import { Avatar, AvatarFallback, AvatarImage } from '@pkmer-music/management/components/ui/avatar'
import { DeleteBtn } from '@pkmer-music/management/components'
import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import tableStyle from '@pkmer-music/management/styles/table.module.scss'
import { pageCollects, deleteCollect } from '@pkmer-music/management/actions'
interface CollectTableProps {
  pageNo: number
  pageSize: number
  query: string
  userId: string
}

const headers = ['ID', '封面', '歌手', '歌曲', '专辑', '操作']

const CollectTable: React.FC<CollectTableProps> = async props => {
  const data = await pageCollects({
    pageNo: props.pageNo,
    pageSize: props.pageSize,
    songName: props.query,
    userId: props.userId
  })

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
          {data.list.length ? (
            data.list.map(collect => (
              <TableRow key={collect.id} className='hover:bg-gray-50'>
                <TableCell className={tableStyle.tableItem}>{collect.id}</TableCell>
                <TableCell className={tableStyle.tableItem}>
                  {/* Avatar,AvatarFallback,AvatarImage */}
                  <Avatar>
                    <AvatarImage src={collect.song.pic} alt={collect.song.name} />
                    <AvatarFallback>{collect.song.name}</AvatarFallback>
                  </Avatar>
                </TableCell>
                <TableCell className={tableStyle.tableItem}>{collect.song.singerName}</TableCell>
                <TableCell className={tableStyle.tableItem}>{collect.song.name}</TableCell>
                <TableCell className={tableStyle.tableItem}>{collect.song.introduction}</TableCell>
                <TableCell className='space-x-2 border border-gray-300 p-4'>
                  {/* 从Table的服务端组件抽离成客户端组件 */}
                  <DeleteBtn
                    id={collect.song.id}
                    triggerDelete={async function (songId: string) {
                      'use server'
                      deleteCollect(songId, props.userId)
                    }}
                  />
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

export default CollectTable
