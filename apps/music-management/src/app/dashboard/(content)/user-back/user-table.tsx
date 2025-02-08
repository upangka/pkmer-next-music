'use client'
import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import { Avatar, AvatarFallback, AvatarImage } from '@pkmer-music/management/components/ui/avatar'
import { Button } from '@pkmer-music/management/components/ui/button'
import tableStyle from './user-table.module.scss'
import type { UserDetail } from '@pkmer-music/management/types'
import { deleteUser } from '@pkmer-music/management/actions'

interface UserTableProps {
  data: UserDetail[]
}

const headers = [
  'ID',
  '用户头像',
  '用户名',
  '性别',
  '手机号码',
  '邮箱',
  '生日',
  '签名',
  '地区',
  '操作'
]

export const UserTable: React.FC<UserTableProps> = ({ data }) => {
  function handleSex(sex?: string) {
    if (sex === 'FEMALE') {
      return '女'
    } else if (sex === 'MALE') {
      return '男'
    }
    return '未知'
  }

  return (
    <>
      <Table className='w-full border-collapse border border-gray-300'>
        <TableHeader>
          <TableRow className='bg-gray-100'>
            {headers.map((header, index) => (
              <TableCell key={index} className='border border-gray-300 p-4 text-left font-semibold'>
                {header}
              </TableCell>
            ))}
          </TableRow>
        </TableHeader>
        <TableBody>
          {data.map(user => (
            <TableRow key={user.id} className='hover:bg-gray-50'>
              <TableCell className={tableStyle.tableItem}>{user.id}</TableCell>
              <TableCell className={tableStyle.tableItem}>
                {/* Avatar,AvatarFallback,AvatarImage */}
                <Avatar>
                  <AvatarImage src={user.avator} alt={user.username} />
                  <AvatarFallback>{user.username}</AvatarFallback>
                </Avatar>
              </TableCell>
              <TableCell className={tableStyle.tableItem}>{user.username}</TableCell>
              <TableCell className={tableStyle.tableItem}>{handleSex(user.sex)}</TableCell>
              <TableCell className={tableStyle.tableItem}>{user.phoneNum}</TableCell>
              <TableCell className={tableStyle.tableItem}>{user.email}</TableCell>
              <TableCell className={tableStyle.tableItem}>{user.birth}</TableCell>
              <TableCell className={tableStyle.tableItem}>{user.introduction}</TableCell>
              <TableCell className={tableStyle.tableItem}>{user.location}</TableCell>
              <TableCell className='space-x-2 border border-gray-300 p-4'>
                <Button className='rounded-md bg-blue-500 px-4 py-1 text-white hover:bg-blue-600'>
                  收藏
                </Button>
                <Button
                  onClick={() => deleteUser(user.id)}
                  className='rounded-md bg-red-500 px-4 py-1 text-white hover:bg-red-600'
                >
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
