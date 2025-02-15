'use client'
import { useState, useRef } from 'react'

export default function Stopwatch() {
  const count = useRef(1)
  const [startTime, setStartTime] = useState<number | null>(null)
  const [now, setNow] = useState<number | null>(null)
  const intervalRef = useRef<number | null>(null)

  function handleStart() {
    if (intervalRef.current) {
      clearInterval(intervalRef.current)
    }
    setStartTime(Date.now())
    setNow(Date.now())

    intervalRef.current = window.setInterval(async () => {
      // 每一次触发渲染
      setNow(Date.now())
      count.current++
      const i = count.current
      // 执行到这里的时候，相当于返回了一个Promise.
      // 相当于这个方法已经执行完毕了，react就抛出渲染去了。
      await new Promise(resolve => setTimeout(resolve, 5000))
      console.log('执行', i)
    }, 1000)
  }

  function handleStop() {
    clearInterval(intervalRef.current!)
    count.current = 1
  }

  let secondsPassed = 0
  if (startTime != null && now != null) {
    secondsPassed = (now - startTime) / 1000
  }

  return (
    <section className='flex h-screen flex-col items-center justify-center gap-2'>
      <h1>Time passed: {secondsPassed.toFixed(3)}</h1>
      <button
        className='border border-gray-500 px-2 py-1 hover:bg-green-600 hover:text-white'
        onClick={handleStart}
      >
        Start
      </button>
      <button
        className='border border-gray-500 px-2 py-1 hover:bg-green-600 hover:text-white'
        onClick={handleStop}
      >
        Stop
      </button>
      <p>渲染{count.current}次</p>
    </section>
  )
}
