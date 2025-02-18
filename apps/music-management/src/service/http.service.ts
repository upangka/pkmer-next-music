'server only'
import { createHttpInstance } from '@pkmer/libs/http'
import { cookies } from 'next/headers'
const getServerSideHttpInstance = createHttpInstance({
  baseURL: process.env.API_URL,
  isServerSide: true,
  getTokenAsync: async function () {
    const cookieStore = await cookies()
    return cookieStore.get('session')?.value || null
  }
})

export const httpServerService = getServerSideHttpInstance()
