import { UserTable } from './user-table'
import { Search, PKMPagination } from '@pkmer-music/management/components'
import { Suspense } from 'react'
import UserLoading from './user-loading'
import { getPageUserTotal } from '@pkmer-music/management/actions'
interface Props {
  searchParams: Promise<{
    [key: string]: string | undefined
  }>
}

/**
 * Page服务端组件，Table服务端组件，Suspense生效
 * 分页客户端组件能够接收服务端组件Page传递的属性。
 * btn组件能够接收Table服务端组件传递的属性，进行交互效果。
 * @param props
 * @returns
 */
export default async function Page(props: Props) {
  const searchParams = await props.searchParams
  const query = searchParams?.query || ''
  const pageNo = +(searchParams?.pageNo || 1)
  const pageSize = +(searchParams?.pageSize || 3)

  const totalData = await getPageUserTotal({ username: query, pageNo, pageSize })

  return (
    <div
      suppressHydrationWarning={true}
      className='w-auto rounded-lg border border-black bg-white p-6 shadow-md'
    >
      <section className='mb-6 flex w-[40%] items-center justify-between gap-5'>
        <h1 className='rounded-md bg-gray-400 p-2 text-lg text-white'>用户列表</h1>
        <Search className='flex-1' placeholder='Search for users' />
      </section>

      {/* TODO suspense的生效问题 */}
      <Suspense key={query + pageNo + Date.now()} fallback={<UserLoading lines={pageSize} />}>
        <UserTable pageNo={pageNo} pageSize={pageSize} query={query} />
      </Suspense>

      <PKMPagination total={totalData.totalPages} />
    </div>
  )
}
