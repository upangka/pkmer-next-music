import { createHttpInstance } from '@pkmer/libs/http'
import { cookies } from 'next/headers'
const getHttpInstance = createHttpInstance({
  baseURL: process.env.API_URL,
  isServerSide: true,
  getTokenAsync: async function () {
    const cookieStore = await cookies()
    return cookieStore.get('token')?.value || null
  }
})

export const httpService = getHttpInstance()
