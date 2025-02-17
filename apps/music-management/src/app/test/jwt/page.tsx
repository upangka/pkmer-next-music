import { verifySessionToken, createSessionToken } from '@pkmer-music/management/lib/jwt'

export default async function Page() {
  const token = await createSessionToken({
    username: 'react'
  })

  const result = await verifySessionToken(token)

  return (
    <>
      <p>{token}</p>
      <section>{result ? 'valid' : 'invalid'}</section>
    </>
  )
}
