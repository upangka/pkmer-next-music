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
const headers = ['ID', '歌单图片', '标题', '简介', '风格', '内容', '评论', '操作']

interface SongListTableProps {
  pageNo: number
  pageSize: number
  query: string
}
export const SongTable: React.FC<SongListTableProps> = async ({ pageNo, pageSize, query }) => {
  const data = await pageSongList({
    pageNo: pageNo,
    pageSize: pageSize,
    title: query
  })

  return (
    <Table className='w-full border border-red-500'>
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
