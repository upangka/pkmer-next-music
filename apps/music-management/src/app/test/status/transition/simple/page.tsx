'use client'
import { useTransition, useState } from 'react'
export default function Page() {
  const [show, startTransition] = useTransition()
  const [count, setCount] = useState(0)
  function handleClick() {
    startTransition(async () => {
      for (let i = 0; i < 6; i++) {
        await new Promise<void>(resolve => setTimeout(() => resolve(), 1000))
        setCount(r => r + 1)
        // startTransition(() => {
        //   setCount(r => r + 1)
        // })
      }
    })
  }
  return (
    <>
      {show && <div>处理中...</div>}
      <p>Count: {count}</p>
      <button onClick={handleClick}>click me</button>
    </>
  )
}
