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
import { pageUsers, deleteSinger } from '@pkmer-music/management/actions'
import { DeleteBtn } from '@pkmer-music/management/components'
interface UserTableProps {
  pageNo: number
  pageSize: number
  query: string
}

const headers = ['ID', '歌手图片', '歌手', '性别', '出生', '地区', '简介', '操作']

export const SingerTable: React.FC<UserTableProps> = async ({ pageNo, pageSize, query }) => {
  const data = await pageUsers({
    pageNo: pageNo,
    pageSize: pageSize,
    username: query
  })

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
                  收藏
                </Button>
                {/* 从Table的服务端组件抽离成客户端组件 */}

                <DeleteBtn id={user.id} triggerDelete={deleteSinger} />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </>
  )
}
