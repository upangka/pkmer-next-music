import dynamic from 'next/dynamic'
const EChartComponent = dynamic(() => import('@pkmer-music/management/components/EChartComponent'))

export default function App() {
  return (
    <>
      <EChartComponent />
    </>
  )
}
