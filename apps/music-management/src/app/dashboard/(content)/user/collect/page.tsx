import CollectTable from './_components/collect-table'
import { SearchHeader, PKMPagination, TableLoading } from '@pkmer-music/management/components'
import { Suspense } from 'react'
import { BreadcrumbClientHelp } from '@pkmer-music/management/context/breadcrumb-client-help'

import { getPageCollectTotal } from '@pkmer-music/management/actions'
interface Props {
  searchParams: Promise<{
    [key: string]: string | undefined
  }>
}

const breadCrumbs = [
  {
    label: '用户管理',
    href: '/dashboard/user'
  },
  {
    label: '收藏管理',
    href: '/dashboard/user/collect',
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
  const userId = searchParams?.userId || 0
  const username = searchParams?.username || ''

  // todo userid为空直接报错
  if (!userId) {
    throw new Error('用户不存在')
  }

  const totalData = await getPageCollectTotal({
    songName: query,
    userId,
    pageNo,
    pageSize
  })

  return (
    <>
      <BreadcrumbClientHelp breadcrumbs={breadCrumbs} />

      <div suppressHydrationWarning={true} className='w-auto rounded-lg bg-white p-6 shadow-md'>
        <SearchHeader>{username}收藏列表</SearchHeader>

        {/* TODO suspense的生效问题 */}
        <Suspense key={query + pageNo + Date.now()} fallback={<TableLoading lines={pageSize} />}>
          <CollectTable userId={userId} pageNo={pageNo} pageSize={pageSize} query={query} />
        </Suspense>

        <PKMPagination total={totalData.totalPages} />
      </div>
    </>
  )
}
