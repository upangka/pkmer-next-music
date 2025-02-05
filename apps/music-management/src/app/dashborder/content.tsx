'use client'
import { PkmerIcon, PkmerBarChart, PkmerPieChart } from '@pkmer-music/management/components'
import contentStyle from './content.module.scss'

import { Bar, Tooltip, YAxis, XAxis, BarChart, CartesianGrid, PieChart, Pie } from 'recharts'

export default function Content() {
  const dataPie = [
    { name: 'Male', value: 50 },
    { name: 'Female', value: 50 }
  ]

  const dataBar = [
    { name: '华语', value: 18 },
    { name: '粤语', value: 10 },
    { name: '欧美', value: 14 },
    { name: '日韩', value: 8 },
    { name: 'BGM', value: 12 },
    { name: '轻音乐', value: 9 },
    { name: '乐器', value: 15 }
  ]

  return (
    <section className='flex-1 py-5'>
      <ul className='flex items-center justify-evenly'>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-blue-600' size={50} icon='uil:user' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-green-600' size={50} icon='iconoir:headset' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>歌曲总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-red-500' size={50} icon='ep:mic' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>歌手数量</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-yellow-600' size={50} icon='mi:document' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>歌单数量</span>
          </div>
        </li>
      </ul>

      {/* Charts start */}
      <section className='grid grid-cols-2 place-items-center'>
        <div>
          <PkmerPieChart dataPie={dataPie} />
        </div>
        <div>
          <PkmerBarChart dataBar={dataBar} />
        </div>

        <div>
          <PkmerBarChart dataBar={dataBar} />
        </div>
        <div>
          <PkmerPieChart dataPie={dataPie} />
        </div>
      </section>
      {/* Charts end */}
    </section>
  )
}
