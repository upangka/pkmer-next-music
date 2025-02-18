import { Search } from '@pkmer-music/management/components'

interface SearchHeaderProps {
  children: React.ReactNode
}

export const SearchHeader: React.FC<SearchHeaderProps> = ({ children }) => {
  return (
    <section className='flex w-[40%] items-center justify-between gap-5'>
      <h1 className='rounded-md bg-gray-400 p-2 text-lg text-white'>{children}</h1>
      <Search className='flex-1' placeholder='Search for users' />
    </section>
  )
}
