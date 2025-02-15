'use client'
import { useParams } from 'next/navigation'

export default function Page() {
  // /test/123/server
  const params = useParams()
  return (
    <>
      客户端组件
      <p>Post: {params.singerId}</p>
    </>
  )
}
