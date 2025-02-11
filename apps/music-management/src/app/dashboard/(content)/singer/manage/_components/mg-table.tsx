import {
  Table,
  TableHeader,
  TableRow,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'

interface MgTableProps {
  pageNo: number
  pageSize: number
  query: string
}

const headers = ['ID', '歌手图片', '歌名', '专辑', '歌词', '资源更新', '评论', '操作']

export const MgTable: React.FC<MgTableProps> = ({ pageNo, pageSize, query }) => {
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
