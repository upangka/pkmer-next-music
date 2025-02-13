'use client'
import { use } from 'react'
import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import { Avatar, AvatarFallback, AvatarImage } from '@pkmer-music/management/components/ui/avatar'
import { Button } from '@pkmer-music/management/components/ui/button'
import tableStyle from '@pkmer-music/management/styles/table.module.scss'
import { pageSinger, deleteSinger } from '@pkmer-music/management/actions'
import { DeleteBtn } from '@pkmer-music/management/components'
import clsx from 'clsx'
import LinkBtn from './LinkButton'
import type { SingerPageQueryRes } from '@pkmer-music/management/types'

interface SingerTableProps {
  pageRes: Promise<SingerPageQueryRes>
}

const headers = ['ID', '歌手图片', '歌手', '性别', '出生', '地区', '简介', '歌曲管理', '操作']

export const SingerTable: React.FC<SingerTableProps> = ({ pageRes }) => {
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
          {data.list.map(singer => (
            <TableRow key={singer.id} className='hover:bg-gray-50'>
              <TableCell className={tableStyle.tableItem}>{singer.id}</TableCell>
              <TableCell className={tableStyle.tableItem}>
                {/* Avatar,AvatarFallback,AvatarImage */}
                <Avatar>
                  <AvatarImage src={singer.pic} alt={singer.name} />
                  <AvatarFallback>{singer.name}</AvatarFallback>
                </Avatar>
              </TableCell>
              <TableCell className={tableStyle.tableItem}>{singer.name}</TableCell>
              <TableCell className={tableStyle.tableItem}>{handleSex(singer.sex)}</TableCell>
              <TableCell className={tableStyle.tableItem}>{singer?.birth.split('T')[0]}</TableCell>
              <TableCell className={tableStyle.tableItem}>{singer.location}</TableCell>
              <TableCell
                className={clsx(
                  tableStyle.tableItem,
                  'max-w-[200px] overflow-hidden text-ellipsis whitespace-nowrap'
                )}
              >
                {singer.introduction}
              </TableCell>
              <TableCell className={tableStyle.tableItem}>
                <LinkBtn href={`/dashboard/singer/manage?singerId=${singer.id}`}>歌曲管理</LinkBtn>
              </TableCell>
              <TableCell className='space-x-2 border border-gray-300 p-4'>
                <Button className='rounded-md bg-blue-500 px-4 py-1 text-white hover:bg-blue-600'>
                  收藏
                </Button>
                {/* 从Table的服务端组件抽离成客户端组件 */}

                <DeleteBtn id={singer.id} triggerDelete={deleteSinger} />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </>
  )
}
