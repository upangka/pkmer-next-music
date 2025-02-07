import {
  Table,
  TableHeader,
  TableRow,
  TableHead,
  TableBody,
  TableCell
} from '@pkmer-music/management/components/ui/table'
import { Button } from '@pkmer-music/management/components/ui/button'
interface Singer {
  id: number
  name: string
  gender: string
  birthDate: string
  region: string
  description: string
  imageUrl: string
}

const sampleData: Singer[] = [
  {
    id: 1,
    name: '张杰',
    gender: '男',
    birthDate: '1982-12-20',
    region: '中国四川',
    description: '华语流行音乐代表歌手...',
    imageUrl: '/images/zhangjie.jpg'
  },
  {
    id: 2,
    name: '周杰伦',
    gender: '男',
    birthDate: '1979-01-08',
    region: '中国台湾',
    description: '著名歌手、作曲家...',
    imageUrl: '/images/zhoujielun.jpg'
  },
  {
    id: 3,
    name: '林允儿',
    gender: '女',
    birthDate: '1990-05-30',
    region: '韩国',
    description: '韩国著名艺人...',
    imageUrl: '/images/linyuner.jpg'
  },
  {
    id: 4,
    name: '陈奕迅',
    gender: '男',
    birthDate: '1974-07-27',
    region: '中国香港',
    description: '华语流行音乐重要人物...',
    imageUrl: '/images/chenyixun.jpg'
  }
]

export default function Page() {
  return (
    <div className='flex-1 border border-black p-8'>
      <h1 className='mb-4 text-2xl font-bold'>歌手管理</h1>
      <Table className='w-full border border-red-500'>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>歌手图片</TableHead>
            <TableHead>歌手</TableHead>
            <TableHead>性别</TableHead>
            <TableHead>出生</TableHead>
            <TableHead>地区</TableHead>
            <TableHead>简介</TableHead>
            <TableHead>操作</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {sampleData.map(singer => (
            <TableRow key={singer.id}>
              <TableCell>{singer.id}</TableCell>
              <TableCell>
                <img
                  src={singer.imageUrl}
                  alt={singer.name}
                  className='h-16 w-16 rounded-full object-cover'
                />
              </TableCell>
              <TableCell>{singer.name}</TableCell>
              <TableCell>{singer.gender}</TableCell>
              <TableCell>{singer.birthDate}</TableCell>
              <TableCell>{singer.region}</TableCell>
              <TableCell>{singer.description}</TableCell>
              <TableCell>
                <div className='flex space-x-2'>
                  <Button variant='outline'>编辑</Button>
                  <Button variant='destructive'>删除</Button>
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}
