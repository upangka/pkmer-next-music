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
  children: React.ReactNode
}
export const PkmerDialog: React.FC<PkmerDialogProps> = props => {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button className='bg-white py-5 text-black hover:bg-blue-600 hover:text-white'>
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
