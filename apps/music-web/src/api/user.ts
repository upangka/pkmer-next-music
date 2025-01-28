import http from './http'
import type { AppResponse } from '@pkmer-music/web//types/http-base'

export interface UserLogin {
  token: string
  username: string
  id: string
}

export interface UserDetails {
  id: string
  username: string
  sex: number
  phoneNum: string
  email: string
  birth: string
  introduction: string
  location: string
}

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

export const getUserDetails = (): AppResponse<UserDetails> => {
  return http.get('/user/detail')
}
