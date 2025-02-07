'use client'
import { useIsServerSide } from './useIsServerSide'
import type { ChartData } from './types'
import { BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Bar } from 'recharts'
interface Props {
  dataBar: ChartData[]
}
export const PkmerBarChart: React.FC<Props> = props => {
  const isServerSide = useIsServerSide()

  if (isServerSide) return null
  return (
    <>
      <BarChart
        width={500}
        height={300}
        data={props.dataBar}
        margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
        barCategoryGap='20%'
      >
        <CartesianGrid strokeDasharray='3 3' horizontal={true} vertical={false} />
        {/* XAxis：定义 X 轴，dataKey='name' 代表 X 轴使用 dataBar 数据中的 name 字段作为标签 */}
        <XAxis dataKey='name' interval={0} padding={{ left: 20, right: 20 }} />
        {/* 定义 Y 轴，自动根据数据范围生成刻度。 */}
        <YAxis />
        <Tooltip />
        <Bar dataKey='value' fill='#82ca9d' />
      </BarChart>
    </>
  )
}
