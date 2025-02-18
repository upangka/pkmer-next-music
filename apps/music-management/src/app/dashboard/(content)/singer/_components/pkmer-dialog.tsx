/**
 * 封裝一下 Dialog，方便使用
 */

import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger
} from '@pkmer-music/management/components/ui/dialog'
import { Button } from '@pkmer-music/management/components/ui/button'

interface PkmerDialogProps {
  title: string
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void
  children: React.ReactNode
}
export const PkmerDialog: React.FC<PkmerDialogProps> = props => {
  return (
    <Dialog open={props.isOpen} onOpenChange={props.onOpenChange}>
      <DialogTrigger asChild>
        <Button
          className='bg-white py-5 text-black hover:bg-blue-600 hover:text-white'
          onClick={() => props.onOpenChange(true)}
        >
          {props.title}
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle className='text-center'>{props.title}</DialogTitle>
        </DialogHeader>
        {props.children}
      </DialogContent>
    </Dialog>
  )
}
