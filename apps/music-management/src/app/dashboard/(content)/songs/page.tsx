import { SongTable } from './_components/songs-table'
import { SearchHeader, PKMPagination, TableLoading } from '@pkmer-music/management/components'
import { Suspense } from 'react'
import { getSongListPageTotal } from '@pkmer-music/management/actions'
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
  const pageSize = +(searchParams?.pageSize || 5)

  const totalData = await getSongListPageTotal({ title: query, pageSize })
  console.log(totalData)
  return (
    <div suppressHydrationWarning={true} className='w-auto rounded-lg bg-white p-6 shadow-md'>
      <SearchHeader>歌单列表</SearchHeader>

      {/* TODO suspense的生效问题 */}
      <Suspense key={query + pageNo + Date.now()} fallback={<TableLoading lines={pageSize} />}>
        <SongTable pageNo={pageNo} pageSize={pageSize} query={query} />
      </Suspense>

      <PKMPagination total={totalData.totalPages} />
    </div>
  )
}
