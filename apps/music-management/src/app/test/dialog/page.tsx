'use client'
import { useState } from 'react'
import TestInput from './_components/test-input'
export default function Page() {
  const [open, setOpen] = useState(false)
  return (
    <section className='flex w-fit flex-col items-center justify-center gap-4 p-2'>
      {/* 修复之前：组件始终在同一个位置，不会因为传递属性的不同就会被React视为不同的组件
      <TestInput open={open} action={() => setOpen(false)} /> */}
      {/* 修复之后：确保组件在UI Tree中处于不同的位置 */}
      {open && <TestInput open={open} action={() => setOpen(false)} />}

      {/* <TestInput key={Date.now().toLocaleString()} 
           open={open} 
           action={() => setOpen(false)} /> */}
      <button
        className='rounded-md border border-black px-2 py-1 hover:bg-blue-600 hover:text-white'
        onClick={() => setOpen(true)}
      >
        Open
      </button>
    </section>
  )
}
