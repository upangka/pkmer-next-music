// import { EChartComponent } from '@pkmer-music/management/components'
'use client'
import dynamic from 'next/dynamic'
const EChartComponent = dynamic(
  () => import('@pkmer-music/management/components/EChartComponent'),
  { ssr: false }
)

export default function App() {
  return (
    <>
      <EChartComponent />
    </>
  )
}
