'use client'
import { Header, Asider } from '@pkmer-music/management/components'
import { useState } from 'react'
import AppProviders from '@pkmer-music/management/context/app-providers'
import { useMenuContext } from '@pkmer-music/management/context/menuContext'
import { Toaster } from '@pkmer-music/management/components/ui/toaster'
import layoutStyle from './layout.module.scss'
import clsx from 'clsx'
export default function DashBoardLayout({
  children
}: Readonly<{
  children: React.ReactNode
}>) {
  const menuContext = useMenuContext()

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
          {children}
        </div>
      </main>
      {/* </AppProviders> */}
    </>
  )
}
