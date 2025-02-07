'use client'
import { useState } from 'react'
import {
  Table,
  TableHeader,
  TableRow,
  TableHead,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import {
  Pagination,
  PaginationContent,
  PaginationEllipsis,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious
} from '@pkmer-music/management/components/ui/pagination'

import { Button } from '@pkmer-music/management/components/ui/button'

interface User {
  id: number
  avatar: string
  username: string
  gender: string
  phone: string
  email: string
  birthday: string
  signature: string
  region: string
}

const userData: User[] = [
  {
    id: 1,
    avatar: '/images/user1.jpg',
    username: 'Yin',
    gender: '女',
    phone: '13776412237',
    email: 'yoona@qq.com',
    birthday: '2019-05-22',
    signature: '好好吃饭',
    region: '山西'
  },
  {
    id: 2,
    avatar: '/images/user2.jpg',
    username: '012',
    gender: '女',
    phone: '13754803255',
    email: 'love@gmail.com',
    birthday: '2019-04-24',
    signature: '我就喜欢吃',
    region: '北京'
  },
  {
    id: 5,
    avatar: '/images/user5.jpg',
    username: '789',
    gender: '女',
    phone: '13634377258',
    email: '666@126.com',
    birthday: '2019-01-08',
    signature: '今天很开心啊',
    region: '山西'
  }
]

export default function UserTable() {
  const [data, setData] = useState<User[]>(userData)

  return (
    <div className='flex-1 rounded-lg bg-white p-6 shadow-md'>
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
          {data.map(user => (
            <TableRow key={user.id} className='hover:bg-gray-50'>
              <TableCell className='border border-gray-300 p-4'>{user.id}</TableCell>
              <TableCell className='border border-gray-300 p-4'>
                <img
                  src={user.avatar}
                  alt={user.username}
                  className='h-12 w-12 rounded-full object-cover'
                />
              </TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.username}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.gender}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.phone}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.email}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.birthday}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.signature}</TableCell>
              <TableCell className='border border-gray-300 p-4'>{user.region}</TableCell>
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

      <Pagination>
        <PaginationContent>
          <PaginationItem>
            <PaginationPrevious href='#' />
          </PaginationItem>
          <PaginationItem>
            <PaginationLink href='#'>1</PaginationLink>
          </PaginationItem>
          <PaginationItem>
            <PaginationEllipsis />
          </PaginationItem>
          <PaginationItem>
            <PaginationNext href='#' />
          </PaginationItem>
        </PaginationContent>
      </Pagination>
    </div>
  )
}
