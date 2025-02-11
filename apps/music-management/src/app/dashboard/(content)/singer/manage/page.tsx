import { MgTable } from './_components/mg-table'
import { SearchHeader, PKMPagination, TableLoading } from '@pkmer-music/management/components'
import { Suspense } from 'react'
import { BreadcrumbClientHelp } from '@pkmer-music/management/context/breadcrumb-client-help'
import { getSongPageQueryTotal, pageQuerySong } from '@pkmer-music/management/actions'

interface Props {
  searchParams: Promise<{
    [key: string]: string | undefined
  }>
}

const breadCrumbs = [
  {
    label: '歌手管理',
    href: '/dashboard/singer'
  },
  {
    label: '歌曲信息',
    href: '/dashboard/singer/manage',
    active: true
  }
]

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
  const singerId = searchParams?.singerId
  if (!singerId) {
    throw new Error('singerId is required')
  }

  const params = {
    pageNo,
    pageSize,
    name: query,
    singerId
  }
  const totalData = await getSongPageQueryTotal(params)

  const data = pageQuerySong(params)

  return (
    <>
      {/* 通过属性传递个客户端组件了 */}
      <BreadcrumbClientHelp breadcrumbs={breadCrumbs} />
      <div suppressHydrationWarning={true} className='w-auto rounded-lg bg-white p-6 shadow-md'>
        <SearchHeader>歌曲信息</SearchHeader>

        {/*  suspense的生效问题 */}
        <Suspense key={query + pageNo + Date.now()} fallback={<TableLoading lines={pageSize} />}>
          <MgTable pageData={data} />
        </Suspense>

        <PKMPagination total={totalData.totalPages} />
      </div>
    </>
  )
}
