import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import { Button } from '@pkmer-music/management/components/ui/button'
import { Avatar, AvatarFallback, AvatarImage } from '@pkmer-music/management/components/ui/avatar'
import { pageUsers } from '@pkmer-music/management/actions'
interface UserTableProps {
  pageNo: number
  pageSize: number
  query: string
}

export const UserTable: React.FC<UserTableProps> = async props => {
  const data = await pageUsers(props)

  return (
    <>
      <Table className='w-full border-collapse border border-gray-300'>
        <TableHeader>
          <TableRow className='bg-gray-100'>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>ID</TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              用户头像
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              用户名
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              性别
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              手机号码
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              邮箱
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              生日
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              签名
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              地区
            </TableCell>
            <TableCell className='border border-gray-300 p-4 text-left font-semibold'>
              操作
            </TableCell>
          </TableRow>
        </TableHeader>
        <TableBody>
          {data.list.map(user => (
            <TableRow key={user.id} className='hover:bg-gray-50'>
              <TableCell className='border border-gray-300 p-4'>{user.id}</TableCell>
              <TableCell className='border border-gray-300 p-4'>
                {/* Avatar,AvatarFallback,AvatarImage */}
                <Avatar>
                  <AvatarImage src={user.avator} alt={user.username} />
                  <AvatarFallback>{user.username}</AvatarFallback>
                </Avatar>
              </TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.username}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.sex}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.phoneNum}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.email}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.birth}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.introduction}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.location}</TableCell>
              <TableCell className='space-x-2 border border-gray-300 p-4'>
                <Button className='rounded-md bg-blue-500 px-4 py-1 text-white hover:bg-blue-600'>
                  收藏
                </Button>
                <Button className='rounded-md bg-red-500 px-4 py-1 text-white hover:bg-red-600'>
                  删除
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </>
  )
}
