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
import { updateSong, init } from '@pkmer-music/management/actions'
import { PkmerForm, PkmerFormItem } from '@pkmer-music/management/components/'
import useComputeFileMd5 from '@pkmer-music/management/hooks/useComputeFileMd5'
interface AddSongProps {
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void //
}

/**
 * 歌曲文件拆分基础流程
 * 1. 当用户选择了文件之后，开始计算文件的md5值
 * 2. 等待前端文件md5计算完成，立即将文件的分片信息交给后端进行处理分片计算
 * 3. 等待后端返回分片信息，前端开始上传分片
 * 4. 等待所有分片上传完成，前端通知后端合并分片
 * @param param0
 * @returns
 */
export const AddSong: React.FC<AddSongProps> = ({ isOpen = false, onOpenChange }) => {
  const [_state, formAction] = useActionState(updateSong, {})
  // 标记文件的分片是否计算完成
  const { computeFileMd5 } = useComputeFileMd5()
  // 上传文件的url
  const upFileRef = useRef('')

  function handleFormSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    startTransition(() => {
      let formData = new FormData(e.currentTarget)
      formData.delete('song')
      formAction(formData)
    })
    onOpenChange(false)
  }

  async function handleUploadFile(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target.files) {
      const file = e.target.files[0]
      const md5 = await computeFileMd5(file)
      await initFileSharePart(file, md5)
    }
  }

  /**
   * 请求后端初始化文件的分片信息
   * @param file
   * @param md5
   */
  async function initFileSharePart(file: File, md5: string) {
    const data = await init({
      fileMd5: md5,
      fullFileName: file.name,
      fileSize: file.size
    })

    console.log('文件分片信息计算结果', data)
  }

  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle className='text-center'>更新歌曲</DialogTitle>
        </DialogHeader>
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
      </DialogContent>
    </Dialog>
  )
}
