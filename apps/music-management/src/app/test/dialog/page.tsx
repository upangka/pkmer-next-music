'use client'
import { useState } from 'react'
import TestInput from './_components/test-input'
export default function Page() {
  const [open, setOpen] = useState(false)
  return (
    <section className='flex flex-col items-center justify-center gap-4'>
      {/* {open && <TestInput open={open} action={() => setOpen(false)} />} */}
      <TestInput open={open} action={() => setOpen(false)} />
      <button
        className='rounded-md border border-black px-2 py-1 hover:bg-blue-600 hover:text-white'
        onClick={() => setOpen(true)}
      >
        Open
      </button>
    </section>
  )
}
