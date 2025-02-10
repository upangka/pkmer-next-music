'use client'
import { Header, Asider, BreadCrumbs } from '@pkmer-music/management/components'
import { useMenuContext } from '@pkmer-music/management/context/menuContext'
import { useBreadCrumbContext } from '@pkmer-music/management/context/breadCrumbContext'
import { Toaster } from '@pkmer-music/management/components/ui/toaster'
import layoutStyle from './layout.module.scss'
import clsx from 'clsx'
export default function DashBoardLayout({
  children
}: Readonly<{
  children: React.ReactNode
}>) {
  const menuContext = useMenuContext()
  const breadCrumbContext = useBreadCrumbContext()

  return (
    <>
      {/* <AppProviders> */}
      <Header onClick={() => menuContext.changeCollapsed(!menuContext.collapsed)} />
      <Toaster />
      <main className='relative my-5 flex h-fit pr-5'>
        <Asider
          className={clsx(layoutStyle.asider, menuContext.collapsed && layoutStyle.collapsed)}
          isShow={menuContext.collapsed}
        />
        <div className={clsx(layoutStyle.content, menuContext.collapsed && layoutStyle.collapsed)}>
          {/* 面包屑start */}
          <BreadCrumbs breadCrumbs={breadCrumbContext.breadcrumbs} />
          {/* 面包屑end */}

          {/* 内容区域start */}
          <section>{children}</section>
          {/* 内容区域end */}
        </div>
      </main>
      {/* </AppProviders> */}
    </>
  )
}
