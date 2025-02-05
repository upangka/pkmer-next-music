import { PieChart, Pie, Cell } from 'recharts'
import type { ChartData } from './types'
import { useIsServerSide } from './useIsServerSide'

interface Props {
  dataPie: ChartData[]
}

const COLORS = [
  '#0088FE', // Bright blue
  '#00C49F', // Green
  '#FFBB28', // Yellow
  '#FF8042', // Orange
  '#A28EFF', // Purple
  '#FF6361', // Pinkish red
  '#E27D60', // Brick red
  '#85C1E9' // Light blue
]

export const PkmerPieChart: React.FC<Props> = ({ dataPie }) => {
  const isServerSide = useIsServerSide()

  if (isServerSide) return null

  const size = dataPie.length
  if (dataPie.length === 0) return <p>Loading chart...</p>
  const targetColors = COLORS.slice(0, size)

  // const cellJsxElements = dataPie.map((_, index) => {
  //   const color = targetColors[index % size]
  //   return <Cell key={index} fill={color} />
  // })

  return (
    <>
      <PieChart id='1' width={400} height={400}>
        <Pie
          id='1'
          data={dataPie}
          cx={200}
          cy={200}
          innerRadius={80}
          outerRadius={100}
          fill='#8884d8'
          dataKey='value'
        >
          {/* {cellJsxElements} */}
        </Pie>
      </PieChart>
    </>
  )
}
