'use client'

import { useParams } from 'next/navigation'

export default function ExampleClientComponent() {
  const params = useParams<{ tag: string; item: string }>()

  // Route -> /shop/[tag]/[item]
  // URL -> /shop/shoes/nike-air-max-97
  // `params` -> { tag: 'shoes', item: 'nike-air-max-97' }
  console.log(params)

  return (
    <>
      <h1>Shop Page</h1>
      <p className='text-red-500'>Tag: {params.tag}</p>
      <p className='text-green-600'>Item: {params.item}</p>
    </>
  )
}
