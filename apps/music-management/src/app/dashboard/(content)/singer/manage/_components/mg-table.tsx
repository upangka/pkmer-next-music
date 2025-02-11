import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import { pageQuerySong } from '@pkmer-music/management/actions'
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
        <TableBody></TableBody>
      </Table>
    </>
  )
}
