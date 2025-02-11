import { UserTable } from './_components/user-table'
import { SearchHeader, PKMPagination, TableLoading } from '@pkmer-music/management/components'
import { Suspense } from 'react'
import { BreadcrumbClientHelp } from '@pkmer-music/management/context/breadcrumb-client-help'
import { getPageUserTotal } from '@pkmer-music/management/actions'
import { pageUsers } from '@pkmer-music/management/actions'

interface Props {
  searchParams: Promise<{
    [key: string]: string | undefined
  }>
}

const breadCrumbs = [
  {
    label: '用户管理',
    href: '/dashboard/user',
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

  const data = pageUsers({
    pageNo: pageNo,
    pageSize: pageSize,
    username: query
  })

  const totalData = await getPageUserTotal({ username: query, pageNo, pageSize })

  return (
    <>
      {/* 通过属性传递个客户端组件了 */}
      <BreadcrumbClientHelp breadcrumbs={breadCrumbs} />
      <div suppressHydrationWarning={true} className='w-auto rounded-lg bg-white p-6 shadow-md'>
        <SearchHeader>用户列表</SearchHeader>

        {/*  suspense的生效问题 */}
        <Suspense key={query + pageNo + Date.now()} fallback={<TableLoading lines={pageSize} />}>
          {/* UserTable是一个客户端组件了，传递了一个server action返回的Promise数据 */}
          <UserTable pageRes={data} />
        </Suspense>

        <PKMPagination total={totalData.totalPages} />
      </div>
    </>
  )
}
