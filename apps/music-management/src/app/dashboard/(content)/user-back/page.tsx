import { UserTable } from './user-table'
import { Search, PKMPagination } from '@pkmer-music/management/components'
import { Suspense } from 'react'
import UserLoading from './user-loading'
import { pageUsers } from '@pkmer-music/management/actions'

interface Props {
  searchParams: Promise<{
    [key: string]: string | undefined
  }>
}
/**
 * Page服务端组件，table客户端组件 Suspense失效
 * @param props
 * @returns
 */
export default async function Page(props: Props) {
  const searchParams = await props.searchParams
  const query = searchParams?.query || ''
  const pageNo = +(searchParams?.pageNo || 1)
  const pageSize = +(searchParams?.pageSize || 3)

  const data = await pageUsers({
    pageNo: pageNo,
    pageSize: pageSize,
    username: query
  })

  return (
    <div className='flex-1 rounded-lg bg-white p-6 shadow-md'>
      <section className='mb-6 flex w-[40%] items-center justify-between gap-5'>
        <h1 className='rounded-md bg-gray-400 p-2 text-lg text-white'>用户列表</h1>
        <Search className='flex-1' placeholder='Search for users' />
      </section>

      {/* TODO suspense的生效问题 */}
      <Suspense key={query + pageNo} fallback={<UserLoading />}>
        <UserTable data={data.list} />
      </Suspense>

      <PKMPagination total={data.totalPages} />
    </div>
  )
}
