'use client'
import { useState, useEffect } from 'react'
import clsx from 'clsx'
export default function Page() {
  const [isShowSecond, setIsShowSecond] = useState(false)
  return (
    <div className='flex flex-col items-center justify-center gap-4 p-10'>
      {/* 就算传递的属性不同但是还是算同一个组件 */}
      {isShowSecond && <Counter isFancy={false} title={'Second'} />}
      {!isShowSecond && <Counter isFancy={true} title={'First'} />}
      <label>
        <input
          type='checkbox'
          checked={isShowSecond}
          onChange={e => {
            setIsShowSecond(e.target.checked)
          }}
        />
        展示第二个组件
      </label>
    </div>
  )
}

interface CounterProps {
  isFancy: boolean
  title: string
}
function Counter({ isFancy, title }: CounterProps) {
  const [score, setScore] = useState(0)

  useEffect(() => {
    console.log(`${title}组件挂载`)

    return () => {
      console.log(`${title}组件卸载`)
    }
  }, [])
  return (
    <div
      className={clsx(
        'flex w-[200px] flex-col items-center justify-center gap-2 rounded-md border-2 p-5 shadow-lg',
        isFancy ? 'border-green-500' : 'border-blue-700'
      )}
    >
      <h1>{title}</h1>
      <h1>{score}</h1>
      <button
        className='rounded-md border border-gray-500 p-2 hover:bg-blue-600 hover:text-white'
        onClick={() => setScore(score + 1)}
      >
        Add one
      </button>
    </div>
  )
}
