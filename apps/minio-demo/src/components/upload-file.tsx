'use client'
import { Icon } from '@iconify-icon/react'
import { useRef, useState } from 'react'

export default function UploadFile() {
  const size = 60
  const inputRef = useRef<HTMLInputElement | null>(null)
  const [fileName, setFileName] = useState('')
  function handleChooseFile() {
    if (inputRef && inputRef.current) {
      console.log('Running???')
      inputRef.current.click()
    }
  }

  function handleFile(e: React.ChangeEvent<HTMLInputElement>) {
    const file = e.target.files?.[0]
    if (file) {
      console.log('Selected file: ', file.name)
      setFileName(file.name)
    }
  }

  function handleSubmit() {
    console.log('Submitting file:', fileName)
    // Add your file submission logic here
  }

  return (
    <section className='w-[50%] border-2 border-black p-5 shadow-md shadow-black'>
      <div>
        <header className='p-3 text-xl font-bold'>Upload file</header>
        <div className='flex flex-col items-center justify-center border-2 border-dashed border-gray-500 p-3'>
          <header className='text-center text-lg'>Select File Here</header>
          <Icon icon='iconamoon:cloud-upload-fill' width={size} height={size} />
          <p className='text-sm text-gray-500'>Files Support audio and video</p>
          <input
            ref={inputRef}
            type='file'
            hidden
            id='file-id'
            accept='audio/*,video/*'
            className='hidden'
            onChange={handleFile}
          />
          <button
            onClick={handleChooseFile}
            className='mt-5 rounded-md bg-blue-500 p-3 text-white hover:bg-blue-700'
          >
            Choose File
          </button>
          {fileName && (
            <section className='m-2 flex w-full flex-col items-center justify-center border-t-2 border-black'>
              <p className='mt-2 text-sm text-green-500'>Selected file: {fileName}</p>
              <button onClick={handleSubmit} className='mt-2 rounded bg-blue-500 p-2 text-white'>
                Submit
              </button>
            </section>
          )}
        </div>
      </div>
    </section>
  )
}
