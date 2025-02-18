'use client'
export default function Page() {
  async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    console.log('观察1', e) // dom

    await Promise.resolve(e.currentTarget)
    console.log('观察2', e.currentTarget)
  }

  return (
    <form onSubmit={handleSubmit}>
      <button type='submit'>提交</button>
    </form>
  )
}
