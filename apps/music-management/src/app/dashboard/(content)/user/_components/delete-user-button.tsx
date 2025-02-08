'use client'
import { Button } from '@pkmer-music/management/components/ui/button'
import { deleteUser } from '@pkmer-music/management/actions'

interface DeleteUserButtonProps {
  userId: string
}

const DeleteUserButton: React.FC<DeleteUserButtonProps> = ({ userId }) => {
  return (
    <Button
      onClick={async () => {
        // 调用server action
        await deleteUser(userId)
        // 弹出确认框
        console.log(`删除${userId}用户成功`)
      }}
      className='rounded-md bg-red-500 px-4 py-1 text-white hover:bg-red-600'
    >
      删除
    </Button>
  )
}
export default DeleteUserButton
