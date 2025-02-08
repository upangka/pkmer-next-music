'use server'
import httpService from '@pkmer-music/management/service'
import { AppResponse } from '@pkmer/libs/http'
import type { UserQuery, UserPageRes, PageTotal } from '@pkmer-music/management/types'
type State = {
  errors?: {
    username?: string[] | undefined
    password?: string[] | undefined
    email?: string[] | undefined
  }
  message?: string
}

export async function login(_preState: State, formData: FormData) {
  const data = Object.fromEntries(formData)
  console.log({ data })

  return {} as State
}
export async function registerUser(_preState: State, formData: FormData) {
  return {} as State
}

export async function pageUsers(query: UserQuery): AppResponse<UserPageRes> {
  // 使用 setTimeout 来实现延迟
  await new Promise(resolve => setTimeout(resolve, 2000)) // 延迟 5 秒

  return httpService.post('/user/page', query)
}

export async function deleteUser(id: string): AppResponse<void> {
  console.log('server action', id)
}

export async function getPageUserTotal(query: UserQuery): AppResponse<PageTotal> {
  return httpService.post('/user/page/total', query)
}
