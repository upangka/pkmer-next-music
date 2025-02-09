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
      <main className='my-5 flex gap-5'>
        <Asider isShow={menuContext.collapsed} />
        <section className='flex-1'>{children}</section>
      </main>
      {/* </AppProviders> */}
    </>
  )
}
