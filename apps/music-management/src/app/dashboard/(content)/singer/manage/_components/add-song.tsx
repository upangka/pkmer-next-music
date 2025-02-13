'use client'
import { useState, useActionState } from 'react'
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger
} from '@pkmer-music/management/components/ui/dialog'
import { Button } from '@pkmer-music/management/components/ui/button'
import { Input } from '@pkmer-music/management/components/ui/input'
import { Label } from '@pkmer-music/management/components/ui/label'
import { addSong } from '@pkmer-music/management/actions'

interface AddSongProps {
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void //
}

export const AddSong: React.FC<AddSongProps> = ({ isOpen = false, onOpenChange }) => {
  const [_state, formAction] = useActionState(addSong, {})

  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Submit Form</DialogTitle>
        </DialogHeader>
        <form action={formAction} className='space-y-4'>
          看见我了吗
        </form>
      </DialogContent>
    </Dialog>
  )
}
