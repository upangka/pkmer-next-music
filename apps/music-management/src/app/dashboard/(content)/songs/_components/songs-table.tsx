'use client'
import { use } from 'react'
import {
  Table,
  TableHeader,
  TableRow,
  TableHead,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import clsx from 'clsx'
import tableStyle from '@pkmer-music/management/styles/table.module.scss'
import { DeleteBtn } from '@pkmer-music/management/components'
import { pageSongList, deleteSongList } from '@pkmer-music/management/actions/songlist'
import type { SongListQueryRes } from '@pkmer-music/management/types'
const headers = ['ID', '歌单图片', '标题', '简介', '风格', '内容', '评论', '操作']

interface SongListTableProps {
  pageData: Promise<SongListQueryRes>
}
export const SongTable: React.FC<SongListTableProps> = ({ pageData }) => {
  const data = use(pageData)

  return (
    <Table className='w-full'>
      <TableHeader>
        <TableRow>
          {headers.map(header => (
            <TableHead key={header}>{header}</TableHead>
          ))}
        </TableRow>
      </TableHeader>
      <TableBody>
        {data.list.map(songlist => (
          <TableRow key={songlist.id}>
            <TableCell className={tableStyle.tableItem}>{songlist.id}</TableCell>
            <TableCell className={tableStyle.tableItem}>
              <img
                src={songlist.pic}
                alt={songlist.title}
                className='h-16 w-16 rounded-full object-cover'
              />
            </TableCell>
            <TableCell className={tableStyle.tableItem}>{songlist.title}</TableCell>
            <TableCell
              className={clsx(
                tableStyle.tableItem,
                'max-w-[200px] overflow-hidden text-ellipsis whitespace-nowrap'
              )}
            >
              {songlist.introduction}
            </TableCell>
            <TableCell className={tableStyle.tableItem}>{songlist?.styles.join(' | ')}</TableCell>
            <TableCell className={tableStyle.tableItem}>
              <button className='rounded-md bg-gray-500 px-3 py-1 text-white'>内容</button>
            </TableCell>
            <TableCell className={tableStyle.tableItem}>
              <button className='rounded-md bg-gray-500 px-3 py-1 text-white'>评论</button>
            </TableCell>
            <TableCell className={tableStyle.tableItem}>
              <div className='flex space-x-2'>
                <button className='rounded-md bg-gray-500 px-3 py-1 text-white'>编辑</button>
                <DeleteBtn id={songlist.id} triggerDelete={deleteSongList} />
              </div>
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  )
}
