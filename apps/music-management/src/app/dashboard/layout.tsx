'use client'
import { Header, Asider } from '@pkmer-music/management/components'
import { useState } from 'react'
import AppProviders from '@pkmer-music/management/context/app-providers'
import { Toaster } from '@pkmer-music/management/components/ui/toaster'
export default function DashBoardLayout({
  children
}: Readonly<{
  children: React.ReactNode
}>) {
  const [collapsed, setCollapsed] = useState(false)

  return (
    <AppProviders>
      <Header onClick={() => setCollapsed(!collapsed)} />
      <Toaster />
      <main className='my-5 flex gap-5'>
        <Asider isShow={collapsed} />
        {children}
      </main>
    </AppProviders>
  )
}
