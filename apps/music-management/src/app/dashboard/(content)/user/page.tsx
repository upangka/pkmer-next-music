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
import { Suspense } from 'react'
interface Props {
  searchParams: Promise<{
    [key: string]: string | undefined
  }>
}
export default async function Page(props: Props) {
  const searchParams = await props.searchParams
  const query = searchParams?.query || ''
  const pageNo = +(searchParams?.pageNo || 1)
  const pageSize = +(searchParams?.pageSize || 20)

  return (
    <div className='flex-1 rounded-lg bg-white p-6 shadow-md'>
      <Suspense key={query + pageNo} fallback={<div>Loading...</div>}>
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
