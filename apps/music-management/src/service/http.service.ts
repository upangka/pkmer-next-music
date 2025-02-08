'server only'
import { createHttpInstance } from '@pkmer/libs/http'
import { cookies } from 'next/headers'
const getServerSideHttpInstance = createHttpInstance({
  baseURL: process.env.API_URL,
  isServerSide: true,
  getTokenAsync: async function () {
    const cookieStore = await cookies()
    return cookieStore.get('token')?.value || null
  }
})

// const getClientSideHttpInstance = createHttpInstance({
//   baseURL: process.env.API_URL,
//   isServerSide: false,
//   getToken: function () {
//     if (typeof window == 'undefined') {
//       throw new Error('getClientSideHttpInstance should be called on client side')
//     }
//     return localStorage.getItem('token') || null
//   }
// })

export const httpServerService = getServerSideHttpInstance()
// export const httpClientService = getClientSideHttpInstance()
