import http from './http'
import type { AppResponse, UserLogin, UserDetails } from '@pkmer-music/web/types'

type UserDetailsWithoutId = Omit<UserDetails, 'id'>

export const login = (email: string, password: string): AppResponse<UserLogin> => {
  const data = { email, password }
  try {
    return http.post('/user/login', data)
  } catch (error) {
    console.error('Login failed:', error)
    throw error
  }
}

export const updateUserDetails = (user: UserDetailsWithoutId): AppResponse<any> => {
  return http.post('/user/detail', user)
}

/**
 * 获取用户详情
 * @returns
 */
export const getUserDetails = (): AppResponse<UserDetails> => {
  return http.get('/user/detail')
}
