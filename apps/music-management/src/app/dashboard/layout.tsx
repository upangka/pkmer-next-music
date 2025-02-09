'use client'
import { Header, Asider } from '@pkmer-music/management/components'
import { useState } from 'react'
import AppProviders from '@pkmer-music/management/context/app-providers'
import { useMenuContext } from '@pkmer-music/management/context/menuContext'
import { Toaster } from '@pkmer-music/management/components/ui/toaster'
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
      <main className='my-5 flex gap-2 bg-slate-100 pr-5'>
        <Asider
          className={'h-auto w-[170px] rounded-md shadow-lg'}
          isShow={menuContext.collapsed}
        />
        <div className='w-full'>{children}</div>
      </main>
      {/* </AppProviders> */}
    </>
  )
}
