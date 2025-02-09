'use client'
import './globals.css'
import AppProviders from '@pkmer-music/management/context/app-providers'

export default function RootLayout({
  children
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang='en'>
      <body className={`antialiased`}>
        <AppProviders>{children}</AppProviders>
      </body>
    </html>
  )
}
