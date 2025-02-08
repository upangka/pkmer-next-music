'use client'

import { MagnifyingGlassIcon } from '@heroicons/react/24/solid'
import { useSearchParams, usePathname, useRouter } from 'next/navigation'
import clsx from 'clsx'
interface Props {
  placeholder: string
  className?: string
}
export const Search: React.FC<Props> = ({ placeholder, className }) => {
  const pathName = usePathname()
  const router = useRouter()
  const searchParams = useSearchParams()
  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const params = new URLSearchParams(searchParams)
    const value = e.target.value.trim()
    if (value) {
      params.set('query', value)
      params.set('page', '1')
    } else {
      params.delete('query')
    }
    router.replace(`${pathName}?${params.toString()}`)
  }

  return (
    <div className={clsx('relative flex flex-1 flex-shrink-0', className)}>
      <label htmlFor='search' className='sr-only'>
        Search
      </label>
      <input
        type='text'
        placeholder={placeholder}
        onChange={handleChange}
        defaultValue={searchParams.get('query') || ''}
        className='peer block w-full rounded-md border border-gray-200 py-[9px] pl-10 outline-2 placeholder:text-gray-500'
      />
      <MagnifyingGlassIcon className='absolute left-3 top-1/2 h-[18px] w-[18px] -translate-y-1/2 text-gray-500 peer-focus:text-gray-900' />
    </div>
  )
}
