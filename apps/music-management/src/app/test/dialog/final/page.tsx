'use client'
import { Fragment, useState, useRef } from 'react'
import MyDialog from './_components/my-dialog'

export default function Page() {
  const [currentActive, setCurrentActive] = useState(0)
  return (
    <section className='flex flex-col items-center justify-center gap-4 p-4'>
      {Array.from({ length: 5 }, (_, index) => {
        return (
          <Fragment key={index}>
            {/* <MyDialog isOpen={isOpen} onOpenChange={setIsOpen} /> */}
            {currentActive == index + 1 && <MyDialog />}
            {/* {currentActive == index + 1 && <p>Show me</p>} */}
            <button
              className='rounded-md border border-black px-2 py-1 hover:bg-blue-600 hover:text-white'
              onClick={() => setCurrentActive(index + 1)}
            >
              Open Dialog {index + 1}
            </button>
          </Fragment>
        )
      })}
    </section>
  )
}
