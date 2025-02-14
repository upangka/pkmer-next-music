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
import { updateSong } from '@pkmer-music/management/actions'
import { PkmerForm, PkmerFormItem } from '@pkmer-music/management/components/'
import useComputeFileMd5 from '@pkmer-music/management/hooks/useComputeFileMd5'
interface AddSongProps {
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void //
}

export const AddSong: React.FC<AddSongProps> = ({ isOpen = false, onOpenChange }) => {
  const [_state, formAction] = useActionState(updateSong, {})
  // 标记文件的分片是否计算完成
  const { fileMd5, computeFileMd5, isComputeFileFinished, clear } = useComputeFileMd5()
  // 上传文件的url
  const upFileRef = useRef('')

  useEffect(() => {
    // 检测到文件的md5值计算完成交给后端处理分片
    if (isComputeFileFinished) {
      console.log('可以上传文件的分片了')
    }
  }, [isComputeFileFinished])

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
      clear()
      const file = e.target.files[0]
      const md5 = await computeFileMd5(file)
      console.log('file-md5: ', md5)
    }
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
            {fileMd5}
          </div>
        </PkmerForm>
      </DialogContent>
    </Dialog>
  )
}
