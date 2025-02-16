'use client'
import { useState, useRef, useEffect } from 'react'
import UploadStatus from '../../dashboard/(content)/singer/manage/_components/upload-status'
import { calculatePercentage } from '@pkmer/libs/utils'

export default function Page() {
  const [uploaded, setUploaded] = useState(0)
  const intervalRef = useRef<number | null>(null)

  function handleClick() {
    clear()
    intervalRef.current = window.setInterval(() => {
      setUploaded(prev => {
        if (prev >= 100) {
          clearInterval(intervalRef.current!)
          return 0
        }
        return prev + 10
      })
    }, 1000)
  }

  function clear() {
    if (intervalRef.current) {
      clearInterval(intervalRef.current)
      setUploaded(0)
    }
  }

  useEffect(() => {
    return () => {
      if (intervalRef.current) {
        clearInterval(intervalRef.current)
      }
    }
  }, [])

  return (
    <section className='mx-auto flex h-screen w-1/2 flex-col items-center justify-center gap-2'>
      <UploadStatus showText uploaded={calculatePercentage(uploaded, 100)} />
      <button
        onClick={handleClick}
        className='rounded-md border border-black px-2 py-1 hover:bg-blue-600 hover:text-white'
      >
        Click Me
      </button>
    </section>
  )
}
