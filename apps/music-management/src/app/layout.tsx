'use client'
import './globals.css'
import AppProviders from '@pkmer-music/management/context/app-providers'
import { Toaster } from '@pkmer-music/management/components/ui/toaster'

export default function RootLayout({
  children
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang='en'>
      <body className={`antialiased`}>
        <AppProviders>{children}</AppProviders>
        <Toaster />
      </body>
    </html>
  )
}
