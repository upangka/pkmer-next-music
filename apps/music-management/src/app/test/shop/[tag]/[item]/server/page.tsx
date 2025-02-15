interface Props {
  params: Promise<{ tag: string; item: string }>
}

const Page: React.FC<Props> = async ({ params }) => {
  // Route -> /shop/[tag]/[item]
  // URL -> /shop/shoes/nike-air-max-97
  // `params` -> { tag: 'shoes', item: 'nike-air-max-97' }
  const { tag, item } = await params
  return (
    <>
      <h1>Shop Page</h1>
      <p className='text-red-500'>Tag: {tag}</p>
      <p className='text-green-600'>Item: {item}</p>
    </>
  )
}

export default Page
