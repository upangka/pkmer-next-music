interface Props {
  lines?: number
}

export default function UserLoading({ lines = 5 }: Props) {
  return (
    <>
      <div className='w-full space-y-4 p-4'>
        {Array.from({ length: lines }).map((_, index) => (
          <div
            key={index}
            className='h-12 w-full animate-pulse rounded-md bg-gradient-to-r from-blue-400 via-pink-400 to-yellow-500'
          ></div>
        ))}
      </div>
    </>
  )
}
