'use client'
import { useActionState, useRef, startTransition, useState, useEffect } from 'react'
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle
} from '@pkmer-music/management/components/ui/dialog'
import { Button } from '@pkmer-music/management/components/ui/button'
import { Input } from '@pkmer-music/management/components/ui/input'
import { updateSong, init, mergeParts } from '@pkmer-music/management/actions'
import { PkmerForm, PkmerFormItem } from '@pkmer-music/management/components/'
import useComputeFileMd5 from '@pkmer-music/management/hooks/useComputeFileMd5'
import { Part } from '@pkmer-music/management/types'
interface AddSongProps {
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void //
}

type AddSongStatus =
  | 'init' // 初始状态
  | 'calcMd5' // 计算文件 MD5
  | 'uploading' // 上传中
  | 'finish' // 完成
  | 'prompt' // 提示状态（用于显示提示信息）
  | 'error' // 错误状态（可选，用于处理异常情况）

/**
 * 歌曲文件拆分基础流程
 * 1. 当用户选择了文件之后，开始计算文件的md5值
 * 2. 等待前端文件md5计算完成，立即将文件的分片信息交给后端进行处理分片计算
 * 3. 等待后端返回分片信息，前端开始上传分片
 * 4. 等待所有分片上传完成，前端通知后端合并分片
 * @returns
 */
export const AddSong: React.FC<AddSongProps> = ({ isOpen = false, onOpenChange }) => {
  const [_state, formAction] = useActionState(updateSong, {})
  const [uploadFilePath, setUploadFilePath] = useState('')
  const [songFile, setSongFile] = useState<File>()
  const [closeTime, setCloseTime] = useState(0)
  // 歌曲处理状态
  const [status, setStatus] = useState<AddSongStatus>('init')
  // 上传状态
  const [uploadStatus, setUploadStatus] = useState({
    uploaded: 0,
    total: 1
  })
  // 处理文件分片md5的计算，用于准备文件
  const { totalParts, completedParts, computeFileMd5 } = useComputeFileMd5()

  async function handleFormSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()

    setStatus('calcMd5')

    // await handleSongFile()
    // TODO 前端再切分片的时候，界面显示初始化中（准备文件中）
    // TODO 实际的上传的时候，显示上传进度
    if (songFile) {
      const md5 = await computeFileMd5(songFile)
      setStatus('uploading')
      await initFileSharePart(songFile, md5)
    }

    // TODO 文件分片上传完成之后，开始上传歌曲信息
    // let formData = new FormData(e.currentTarget)
    // formData.delete('song')
    // formAction(formData)
    setStatus('prompt')
    setCloseTime(5)
    setInterval(() => {
      setCloseTime(prev => prev - 1)
    }, 1000)
    setTimeout(() => {
      console.log('5秒之后关闭')
      setStatus('finish')
      onOpenChange(false)
    }, 5000)
  }

  function handleUploadFile(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target.files) {
      const file = e.target.files[0]
      setSongFile(file)
      // TODO 如果文件<10MB，则直接上传，否则走分片上传
      // const md5 = await computeFileMd5(file)
      // await initFileSharePart(file, md5)
    }
  }

  /**
   * 请求后端初始化文件的分片信息
   * @param file
   * @param md5
   *
   */
  async function initFileSharePart(file: File, md5: string) {
    console.log('开始请求后端计算文件分片信息')
    const data = await init({
      fileMd5: md5,
      fullFileName: file.name,
      fileSize: file.size
    })

    console.log('文件分片信息计算结果', data)

    if (data.finished) {
      // 秒传
      setUploadStatus({
        uploaded: 1,
        total: 1
      })
      console.log('秒传成功')
      // 上传的oss对象的url bucket + buketPath + fileName
      const ossUrl = '/' + data.bucket + '/' + data.bucketPath + '/' + data.fileName
      console.log('上传的oss对象的url', ossUrl)
      setUploadFilePath(ossUrl)
    } else {
      // 分片上传
      const parts = data.parts
      const upResults = await uploadFileParts(parts, file)

      if (upResults.total === upResults.successCount) {
        // 分片全部上传成功合并分片
        // TODO 后端去做
        const etags = [...Array(data.partNumber).keys()].map(_ => '')
        // const etags = parts.map(part => part.etag)
        await merge(md5, etags)
      } else {
        console.log('分片上传失败,准备测试断点续传')
      }
    }
  }

  /**
   * 分片上传
   * @param parts
   * @param file
   */
  async function uploadFileParts(parts: Part[], file: File) {
    console.log('开始分片上传')
    // 控制并发数量
    let currentLimit = 2
    let count = 0
    let successCount = 0
    let errorCount = 0
    setUploadStatus({
      ...uploadStatus,
      total: parts.length
    })
    while (count < parts.length) {
      const currentBatch = parts.slice(count, count + currentLimit)
      const requests = currentBatch.map(async (part, index) => {
        const chunckFile = file.slice(part.shardingStart, part.shardingEnd)
        try {
          const r = await fetch(part.uploadUrl, {
            method: 'put',
            body: chunckFile
          })
          console.log('分片上传', part.partNumber)
          return r
        } catch (err) {
          console.error(err)
          return err as any
        }
      })

      const results = await Promise.allSettled(requests)

      results.map(r => {
        if (r.status === 'fulfilled') {
          console.log('成功上传', ++successCount)
          setUploadStatus(prev => ({
            ...prev,
            uploaded: prev.uploaded + 1
          }))
        } else if (r.status === 'rejected') {
          console.log('失败上传', ++errorCount)
        }
      })

      await new Promise(resolve => setTimeout(resolve, 1000))
      count += currentLimit
      // TODO 测试断点续传
      // break
    }

    console.log(`一共${parts.length}个分片,成功上传${successCount},上传失败${errorCount}个`)
    return {
      total: parts.length,
      successCount,
      errorCount
    }
  }

  /**
   * 通知后端合并分片
   * @param fileMd5
   */
  async function merge(fileMd5: string, etags: string[]) {
    const url = await mergeParts(fileMd5, etags)
    console.log('生成的预访问链接', url)
  }

  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle className='text-center'>更新歌曲</DialogTitle>
        </DialogHeader>

        {status === 'calcMd5' && (
          <section className='flex flex-col gap-3'>
            <p>
              文件初始化
              <span>{((completedParts / totalParts) * 100).toFixed(2)}%</span>
            </p>
          </section>
        )}
        {status === 'prompt' && (
          <p className='rounded-md border border-black py-2 text-center shadow-lg'>
            <span className='text-lg text-red-600'>{closeTime}秒</span>之后关闭
          </p>
        )}
        {status === 'uploading' && (
          <p>{((uploadStatus.uploaded / uploadStatus.total) * 100).toFixed(2)}%</p>
        )}
        {status === 'init' && (
          <PkmerForm onSubmit={handleFormSubmit} className='space-y-4'>
            <PkmerFormItem label='歌曲名称'>
              <Input name='name' placeholder='歌曲名称' />
            </PkmerFormItem>
            <PkmerFormItem label='专辑'>
              <Input name='introduction' placeholder='专辑' />
            </PkmerFormItem>

            <PkmerFormItem label='上传歌曲'>
              <Input
                name='song'
                className='flex items-center justify-center !border-none'
                type='file'
                accept='audio/*'
                placeholder='歌曲'
                onChange={handleUploadFile}
              />
            </PkmerFormItem>
            <PkmerFormItem label='上传歌词'>
              <Input
                name='lyricFile'
                className='flex items-center justify-center'
                type='file'
                accept='.lrc, .txt'
                placeholder='歌词'
              />
            </PkmerFormItem>
            <div>
              <Button type='submit'>提交</Button>
            </div>
          </PkmerForm>
        )}
      </DialogContent>
    </Dialog>
  )
}
