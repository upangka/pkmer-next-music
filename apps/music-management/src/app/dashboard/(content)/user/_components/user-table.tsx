'use client'
import { use, useState } from 'react'
import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import Link from 'next/link'
import { Avatar, AvatarFallback, AvatarImage } from '@pkmer-music/management/components/ui/avatar'
import { Button } from '@pkmer-music/management/components/ui/button'
import tableStyle from '@pkmer-music/management/styles/table.module.scss'
import { deleteUser } from '@pkmer-music/management/actions'
import { DeleteBtn } from '@pkmer-music/management/components'
import type { UserPageRes } from '@pkmer-music/management/types'

interface UserTableProps {
  pageRes: Promise<UserPageRes>
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

export const UserTable: React.FC<UserTableProps> = ({ pageRes }) => {
  const [checkList, setCheckList] = useState<string[]>([])
  const data = use(pageRes)

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
          {data.list.map(user => (
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
                  <Link
                    href={`/dashboard/user/collect?username=${user.username}&userId=${user.id}`}
                  >
                    收藏
                  </Link>
                </Button>
                {/* 从Table的服务端组件抽离成客户端组件 */}
                <DeleteBtn
                  id={user.id}
                  triggerDelete={async id => {
                    // 'use server'
                    await deleteUser(id)
                  }}
                />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </>
  )
}
