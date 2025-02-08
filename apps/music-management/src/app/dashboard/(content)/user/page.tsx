import {
  Pagination,
  PaginationContent,
  PaginationEllipsis,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious
} from '@pkmer-music/management/components/ui/pagination'
import { UserTable } from './user-table'
import { Search } from '@pkmer-music/management/components'
import { Suspense } from 'react'
import UserLoading from './user-loading'
interface Props {
  searchParams: Promise<{
    [key: string]: string | undefined
  }>
}
export default async function Page(props: Props) {
  const searchParams = await props.searchParams
  const query = searchParams?.query || ''
  const pageNo = +(searchParams?.pageNo || 1)
  const pageSize = +(searchParams?.pageSize || 10)

  return (
    <div className='flex-1 rounded-lg bg-white p-6 shadow-md'>
      <section className='mb-6 flex w-[40%] items-center justify-between gap-5'>
        <h1 className='rounded-md bg-gray-400 p-2 text-lg text-white'>用户列表</h1>
        <Search className='flex-1' placeholder='Search for users' />
      </section>

      <Suspense key={query + pageNo} fallback={<UserLoading />}>
        <UserTable pageNo={pageNo} pageSize={pageSize} query={query} />
      </Suspense>
      <Pagination>
        <PaginationContent>
          <PaginationItem>
            <PaginationPrevious href='#' />
          </PaginationItem>
          <PaginationItem>
            <PaginationLink href='#'>1</PaginationLink>
          </PaginationItem>
          <PaginationItem>
            <PaginationEllipsis />
          </PaginationItem>
          <PaginationItem>
            <PaginationNext href='#' />
          </PaginationItem>
        </PaginationContent>
      </Pagination>
    </div>
  )
}
