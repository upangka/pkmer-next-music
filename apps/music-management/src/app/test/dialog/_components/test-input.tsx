'use client'
import { useEffect } from 'react'

import { ReactEventHandler, useState } from 'react'
interface Props {
  open: boolean
  action: ReactEventHandler<HTMLButtonElement>
}

const TestInput: React.FC<Props> = ({ open, action }) => {
  const [msg, setMsg] = useState('')

  //   方式1
  const style: React.CSSProperties = {
    display: open ? 'block' : 'none'
  }

  useEffect(() => {
    console.log('TestInput 挂载')
    return () => {
      console.log('TestInput 卸载')
    }
  }, [])

  return (
    <section
      // 方式1
      style={style}
      className='flex h-fit w-fit flex-col items-center justify-start gap-3 rounded-md border border-black p-5 shadow-lg'
    >
      {open && <p className='text-center text-lg font-bold text-green-400'>内容:{msg}</p>}
      <input className='border border-black' value={msg} onChange={e => setMsg(e.target.value)} />

      <button
        className='rounded-md border border-black px-2 py-1 hover:bg-blue-600 hover:text-white'
        onClick={action}
      >
        Close
      </button>
    </section>
  )
}

export default TestInput
