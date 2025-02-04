'use client'
import { useEffect, useRef } from 'react'
import * as echarts from 'echarts'

export const EChartComponent = () => {
  const chartRef = useRef(null)

  useEffect(() => {
    // 确保在浏览器环境中运行
    if (typeof window !== 'undefined') {
      const chart = echarts.init(chartRef.current)

      const option = {
        title: {
          text: 'ECharts 示例'
        },
        xAxis: {
          data: ['A', 'B', 'C', 'D', 'E']
        },
        yAxis: {},
        series: [
          {
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10]
          }
        ]
      }

      chart.setOption(option)

      return () => {
        chart.dispose()
      }
    }
  }, [])

  return <div ref={chartRef} style={{ width: '100%', height: '400px' }} />
}

export default EChartComponent
