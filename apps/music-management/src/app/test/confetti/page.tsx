'use client'
import useConfetti from '@pkmer-music/management/hooks/use-confetti'

export default function Page() {
  const { showConfetti } = useConfetti(60, 6)
  return (
    <>
      <div
        onClick={() => showConfetti()}
        className='flex h-screen w-screen items-center justify-center bg-black'
      >
        <h1>Hello React! ⚛️</h1>
        <p>Building user interfaces.</p>
      </div>
    </>
  )
}
