'use client'
import { useState } from 'react'
export default function Page() {
  const [count, setCount] = useState(0)

  async function handleClick() {
    for (let i = 0; i < 100; i++) {
      const r = await new Promise<number>(resolve => setTimeout(() => resolve(i), 1000))
      console.log(new Date().toISOString(), '处理完成一个分片任务')
      // 这里是一个异步的，并不会等到for循环结束，
      // 才统一渲染
      setCount(r => r + 1)
    }
    console.log('for循环运行结束')
  }

  console.log('渲染组件')

  return (
    <section className='flex h-screen w-full flex-col items-center justify-center gap-4 border border-red-500'>
      <h1>串行执行</h1>
      <p>count: {count}</p>
      <p>uploading: {Number((count / 100).toFixed(2)) * 100}%</p>
      <button className='rounded-md border border-black px-4 py-2' onClick={handleClick}>
        开始
      </button>
    </section>
  )
}
