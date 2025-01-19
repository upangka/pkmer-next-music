'use client'
import { Icon } from '@iconify-icon/react'

export default function UploadFile() {
  const size = 60
  return (
    <section className='w-[50%] border-2 border-black p-5 shadow-md shadow-black'>
      <div>
        <header className='p-3 text-xl font-bold'>Upload file</header>
        <div className='flex flex-col items-center justify-center border-2 border-dashed border-gray-500 p-3'>
          <header className='text-center text-lg'>Select File Here</header>
          <Icon icon='iconamoon:cloud-upload-fill' width={size} height={size} />
          <p className='text-sm text-gray-500'>Files Support mp3,video</p>
          <button className='mt-5 rounded-md bg-blue-500 p-3 text-white hover:bg-blue-700'>
            Choose File
          </button>
        </div>
      </div>
    </section>
  )
}
