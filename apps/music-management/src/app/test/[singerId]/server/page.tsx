interface Props {
  params: Promise<{ singerId: string }>
}

const Page: React.FC<Props> = async ({ params }) => {
  const id = (await params).singerId
  return (
    <>
      服务端组件
      <div>My Post: {id}</div>
    </>
  )
}

export default Page
