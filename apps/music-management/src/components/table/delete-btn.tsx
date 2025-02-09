'use client'
import { Button } from '@pkmer-music/management/components/ui/button'
import { useToast } from '@pkmer-music/management/hooks/use-toast'
import { ToastAction } from '@pkmer-music/management/components/ui/toast'
import { useRouter, useSearchParams, usePathname } from 'next/navigation'
import {
  Dialog,
  DialogTrigger,
  DialogContent,
  DialogTitle,
  DialogHeader,
  DialogDescription,
  DialogFooter,
  DialogClose
} from '@pkmer-music/management/components/ui/dialog'

interface DeleteUserButtonProps {
  id: string
  triggerDelete: (id: string) => Promise<void>
}

export const DeleteBtn: React.FC<DeleteUserButtonProps> = ({ id, triggerDelete }) => {
  const { toast } = useToast()
  const router = useRouter()
  const path = usePathname()
  const searchParams = useSearchParams()

  const params = new URLSearchParams(searchParams)

  async function handleDelete() {
    try {
      await triggerDelete(id)
      toast({
        title: '删除成功',
        description: '删除成功',
        action: <ToastAction altText='删除成功'>删除成功</ToastAction>
      })
      router.push(`${path}?${params.toString()}`)
    } catch (_) {
      toast({
        title: '删除失败',
        description: '删除失败，请稍后再试',
        action: <ToastAction altText='删除失败'>删除失败</ToastAction>
      })
    }
  }

  return (
    <>
      <Dialog>
        <DialogTrigger asChild>
          <Button variant='outline' className='bg-red-600 text-white'>
            删除用户
          </Button>
        </DialogTrigger>
        <DialogContent className='sm:max-w-[425px]'>
          <DialogHeader>
            <DialogTitle>确认删除用户?</DialogTitle>
            <DialogDescription>删除用户后，该用户的所有信息将无法恢复。</DialogDescription>
          </DialogHeader>

          <DialogFooter className='flex !justify-evenly'>
            <DialogClose asChild>
              <Button type='button' variant='secondary'>
                取消
              </Button>
            </DialogClose>
            <DialogClose asChild>
              <Button type='button' variant='secondary' onClick={handleDelete}>
                确定
              </Button>
            </DialogClose>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </>
  )
}
