'use client'
import { useState } from 'react'

// 限流
const currencyLimit = 3
// 100分片
const partsNumber = 100
export default function Page() {
  // 定义一个状态变量count，初始值为0
  const [count, setCount] = useState(0)
  // 定义一个状态变量success，初始值为0
  const [success, setSuccess] = useState(0)
  const [fail, setFail] = useState(0)

  async function handleClick() {
    let index = 0
    // 模拟100分分片
    const files = Array(partsNumber).fill(0)

    // 并行三个
    while (index < partsNumber) {
      const map = files.slice(index, index + currencyLimit).map(
        _ =>
          new Promise<void>(resolve => {
            setTimeout(() => {
              console.log(new Date().toISOString(), '处理完成一个分片任务')
              // 这里是一个异步的，并不会等到for循环结束，
              // 才统一渲染
              setCount(r => r + 1)
              resolve()
            }, 1000)
          })
      )

      const res = await Promise.allSettled(map)

      res.forEach(r => {
        if (r.status === 'rejected') {
          console.log('上传分片失败', r.reason)
          setFail(r => r + 1)
        } else if (r.status === 'fulfilled') {
          console.log('上传分片成功')
          setSuccess(r => r + 1)
        }
      })

      index += currencyLimit
    }
    console.log('while循环运行结束')
  }

  console.log('渲染组件')

  return (
    <section className='flex h-screen w-full flex-col items-center justify-center gap-4 border border-red-500'>
      <h1>限流3</h1>
      <p>count: {count}</p>
      <p>uploading: {Number((count / partsNumber).toFixed(2)) * 100}%</p>
      <button className='rounded-md border border-black px-4 py-2' onClick={handleClick}>
        开始
      </button>

      <ul className='flex flex-col gap-2'>
        <li className='text-blue-600'>总数： {partsNumber}</li>
        <li className='text-green-600'>上传成功: {success}</li>
        <li className='text-red-600'>上传失败： {fail}</li>
      </ul>
    </section>
  )
}
