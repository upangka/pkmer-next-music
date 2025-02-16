'use client'
import { useEffect } from 'react'

import { ReactEventHandler, useState } from 'react'
interface Props {
  open: boolean
  action: ReactEventHandler<HTMLButtonElement>
}

const TestInput: React.FC<Props> = ({ open, action }) => {
  const [msg, setMsg] = useState('')

  const style: React.CSSProperties = {
    display: open ? 'block' : 'none'
  }

  useEffect(() => {
    console.log('TestInput mount')
    return () => {
      console.log('TestInput unmount')
    }
  }, [])

  return (
    <section
      style={style}
      className='flex w-1/4 flex-col gap-3 rounded-md border border-black p-5 shadow-lg'
    >
      {open && <p className='text-center text-lg font-bold text-green-400'>内容:{msg}</p>}
      <input className='border border-black' onChange={e => setMsg(e.target.value)} />

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
