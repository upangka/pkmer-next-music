import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import { Avatar, AvatarFallback, AvatarImage } from '@pkmer-music/management/components/ui/avatar'

import { pageQuerySong } from '@pkmer-music/management/actions'
import tableStyle from '@pkmer-music/management/styles/table.module.scss'
interface MgTableProps {
  pageNo: number
  pageSize: number
  query: string
  singerId: string
}

const headers = ['ID', '歌曲图片', '歌名', '专辑', '资源更新', '评论', '操作']

export const MgTable: React.FC<MgTableProps> = async ({ pageNo, pageSize, query, singerId }) => {
  const data = await pageQuerySong({
    pageNo,
    pageSize,
    name: query,
    singerId
  })

  console.log(data)

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
          {data.list.length > 0 ? (
            data.list.map(song => (
              <TableRow key={song.id} className='hover:bg-gray-50'>
                <TableCell className={tableStyle.tableItem}>{song.id}</TableCell>
                <TableCell className={tableStyle.tableItem}>
                  {/* Avatar,AvatarFallback,AvatarImage */}
                  <Avatar>
                    <AvatarImage src={song.pic} alt={song.name} />
                    <AvatarFallback>{song.name}</AvatarFallback>
                  </Avatar>
                </TableCell>
                <TableCell className={tableStyle.tableItem}>{song.name}</TableCell>
                <TableCell className={tableStyle.tableItem}>{song.introduction}</TableCell>
                <TableCell className={tableStyle.tableItem}>
                  <div className='flex flex-col items-center justify-center gap-2'>
                    <button>更新图片</button>
                    <button>更新歌曲</button>
                  </div>
                </TableCell>
                <TableCell className={tableStyle.tableItem}>
                  <button>评论</button>
                </TableCell>
                <TableCell className={tableStyle.tableItem}>
                  <div className='flex flex-col items-center justify-center gap-2'>
                    <button>编辑</button>
                    <button>删除</button>
                  </div>
                </TableCell>
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell
                colSpan={headers.length}
                className='border border-gray-300 py-20 text-center text-2xl text-gray-500'
              >
                Empty
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </>
  )
}
