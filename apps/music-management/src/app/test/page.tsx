'use client'
import { Card, CardContent } from '@pkmer-music/management/components/ui/card'
import { PieChart, Pie, Cell, BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip } from 'recharts'
import { User, Headphones, Mic, FileText } from 'lucide-react'

const dataPie = [
  { name: 'Male', value: 50 },
  { name: 'Female', value: 50 }
]

const COLORS = ['#0088FE', '#00C49F']

const dataBar = [
  { name: '华语', value: 18 },
  { name: '粤语', value: 10 },
  { name: '欧美', value: 14 },
  { name: '日韩', value: 8 },
  { name: 'BGM', value: 12 },
  { name: '轻音乐', value: 9 },
  { name: '乐器', value: 15 }
]

const Dashboard = () => {
  return (
    <div className='min-h-screen bg-gray-50 p-8'>
      {/* Header */}
      <h1 className='mb-6 text-2xl font-bold'>Yin-music 后台管理</h1>

      {/* Statistics Cards */}
      <div className='mb-8 grid grid-cols-4 gap-4'>
        <Card className='flex items-center bg-white p-4 shadow-lg'>
          <User className='mr-4 text-blue-500' size={32} />
          <div>
            <p className='text-gray-600'>用户总数</p>
            <p className='text-xl font-bold'>19</p>
          </div>
        </Card>

        <Card className='flex items-center bg-white p-4 shadow-lg'>
          <Headphones className='mr-4 text-green-500' size={32} />
          <div>
            <p className='text-gray-600'>歌曲总数</p>
            <p className='text-xl font-bold'>114</p>
          </div>
        </Card>

        <Card className='flex items-center bg-white p-4 shadow-lg'>
          <Mic className='mr-4 text-red-500' size={32} />
          <div>
            <p className='text-gray-600'>歌手数量</p>
            <p className='text-xl font-bold'>43</p>
          </div>
        </Card>

        <Card className='flex items-center bg-white p-4 shadow-lg'>
          <FileText className='mr-4 text-yellow-500' size={32} />
          <div>
            <p className='text-gray-600'>歌单数量</p>
            <p className='text-xl font-bold'>85</p>
          </div>
        </Card>
      </div>

      {/* Charts */}
      <div className='grid grid-cols-2 gap-8'>
        <div>
          <h2 className='mb-4 text-lg font-semibold'>用户性别比例</h2>
          <PieChart width={300} height={300}>
            <Pie
              data={dataPie}
              cx={150}
              cy={150}
              innerRadius={60}
              outerRadius={80}
              fill='#8884d8'
              paddingAngle={5}
              dataKey='value'
            >
              {dataPie.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
          </PieChart>
        </div>

        <div>
          <h2 className='mb-4 text-lg font-semibold'>歌曲类型</h2>
          <BarChart
            width={400}
            height={300}
            data={dataBar}
            margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
          >
            <CartesianGrid strokeDasharray='3 3' />
            <XAxis dataKey='name' />
            <YAxis />
            <Tooltip />
            <Bar dataKey='value' fill='#82ca9d' />
          </BarChart>
        </div>
      </div>
    </div>
  )
}

export default Dashboard
