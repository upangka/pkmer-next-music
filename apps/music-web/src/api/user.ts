import http from './http'
import type { AppResponse } from '@pkmer-music/web//types/http-base'

export const login = async (email: string, password: string): AppResponse<string> => {
  const data = { email, password }
  try {
    return http.post<string>('/user/login', data)
  } catch (error) {
    console.error('Login failed:', error)
    throw error
  }
}
