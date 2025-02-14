'use client'
import { useActionState, startTransition } from 'react'
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle
} from '@pkmer-music/management/components/ui/dialog'
import { Button } from '@pkmer-music/management/components/ui/button'
import { Input } from '@pkmer-music/management/components/ui/input'
import { addSong } from '@pkmer-music/management/actions'
import { PkmerForm, PkmerFormItem } from '@pkmer-music/management/components/'

interface AddSongProps {
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void //
}

export const AddSong: React.FC<AddSongProps> = ({ isOpen = false, onOpenChange }) => {
  const [_state, formAction] = useActionState(addSong, {})
  function handleFormSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    startTransition(() => {
      formAction(new FormData(e.currentTarget))
    })
    onOpenChange(false)
  }
  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle className='text-center'>更新歌曲</DialogTitle>
        </DialogHeader>
        <PkmerForm onSubmit={handleFormSubmit} className='space-y-4'>
          <PkmerFormItem label='歌曲名称'>
            <Input name='songName' placeholder='歌曲名称' />
          </PkmerFormItem>
          <PkmerFormItem label='专辑'>
            <Input name='introduce' placeholder='专辑' />
          </PkmerFormItem>

          <PkmerFormItem label='上传歌曲'>
            <Input
              name='song'
              className='flex items-center justify-center !border-none'
              type='file'
              placeholder='歌曲'
            />
          </PkmerFormItem>
          <PkmerFormItem label='上传歌词'>
            <Input
              name='lyric'
              className='flex items-center justify-center'
              type='file'
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
