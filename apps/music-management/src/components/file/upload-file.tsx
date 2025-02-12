'use client'
import { Icon } from '@iconify-icon/react'
import { useRef, useState } from 'react'
import useComputeFileMd5 from '@pkmer-music/management/hooks/useComputeFileMd5'

export default function UploadFile() {
  const size = 60
  const inputRef = useRef<HTMLInputElement | null>(null)
  const [fileName, setFileName] = useState('')
  const { fileMd5, computeFileMd5, clear } = useComputeFileMd5()

  const showSubContext = !!fileName
  const showSubmitBtn = fileName && fileMd5

  function handleChooseFile() {
    if (inputRef.current) {
      reset()
      inputRef.current.click()
    }
  }

  function reset() {
    setFileName('')
    clear()
  }

  async function handleFile(e: React.ChangeEvent<HTMLInputElement>) {
    const file = e.target.files?.[0]
    if (file) {
      setFileName(file.name)
      try {
        await computeFileMd5(file)
      } catch (err) {
        console.error('Error computing file MD5:', err)
      }
    }
  }

  function handleSubmit() {
    console.log('Submitting file:', fileName)
    // todo 剩下在项目中做
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
            onChange={handleFile}
          />
          <button
            onClick={handleChooseFile}
            className='mt-5 rounded-md bg-blue-500 p-3 text-white hover:bg-blue-700'
          >
            Choose File
          </button>

          {showSubContext && (
            <section className='m-2 flex w-full flex-col items-center justify-center border-t-2 border-black'>
              <p className='mt-2 text-sm text-green-500'>Selected file: {fileName}</p>
              {fileMd5 && <section>文件的md5:{fileMd5}</section>}
              {showSubmitBtn && (
                <button
                  onClick={handleSubmit}
                  className='mt-5 rounded-md bg-blue-500 p-3 text-white hover:bg-blue-700'
                >
                  Upload
                </button>
              )}
            </section>
          )}
        </div>
      </div>
    </section>
  )
}
