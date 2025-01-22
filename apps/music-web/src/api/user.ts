import http from './http'
import type { AppResponse } from '@pkmer-music/web//types/http-base'

export interface UserLogin {
  token: string
  username: string
  id: string
}

export const login = (email: string, password: string): AppResponse<UserLogin> => {
  const data = { email, password }
  try {
    return http.post('/user/login', data)
  } catch (error) {
    console.error('Login failed:', error)
    throw error
  }
}
