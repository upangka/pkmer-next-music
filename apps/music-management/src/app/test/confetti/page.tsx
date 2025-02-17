'use client'
import useConfetti from '@pkmer-music/management/hooks/use-confetti'

export default function Page() {
  const { show } = useConfetti()
  return (
    <>
      <div
        onClick={() => show()}
        className='flex h-screen w-screen items-center justify-center bg-black'
      >
        <h1>Hello React! ⚛️</h1>
        <p>Building user interfaces.</p>
      </div>
    </>
  )
}
