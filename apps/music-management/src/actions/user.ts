'use server'
import httpService from '@pkmer-music/management/service'
import { AppResponse } from '@pkmer/libs/http'
import z from 'zod'
import { redirect } from 'next/navigation'
import { createSession } from '@pkmer-music/management/lib/session'
import type {
  UserQuery,
  UserPageRes,
  AdminLoginView,
  PageTotal
} from '@pkmer-music/management/types'

const FormScheme = z.object({
  username: z.string().min(1, { message: 'Username is required' }),
  password: z.string().min(1, { message: 'Password is required' })
})

type State = {
  errors?: {
    username?: string[] | undefined
    password?: string[] | undefined
    email?: string[] | undefined
  }
  message?: string
  data?: AdminLoginView
}

export async function login(_preState: State, formData: FormData): Promise<State> {
  const rawFormData = Object.fromEntries(formData)

  const validateFields = FormScheme.safeParse(rawFormData)
  if (!validateFields.success) {
    console.log({ rawFormData })
    return {
      errors: validateFields.error.flatten().fieldErrors
    } satisfies State
  }

  try {
    const loginResult = await httpService.post<AdminLoginView>(
      '/admin/user/login',
      validateFields.data
    )
    createSession(loginResult.token)
    return { data: loginResult } satisfies State
  } catch (e) {
    console.error(e)
    return { message: '登录失败' } satisfies State
  }

  // redirect('/dashboard')
  return {} as State
}

/**
 * 注册
 * @param _preState
 * @param formData
 * @returns
 */
export async function registerUser(_preState: State, formData: FormData) {
  return { message: '注册功能暂未开放' } as State
}

export async function pageUsers(query: UserQuery): AppResponse<UserPageRes> {
  // 使用 setTimeout 来实现延迟
  await new Promise(resolve => setTimeout(resolve, 3500)) // 延迟 5 秒

  return httpService.post('/user/page', query)
}

export async function getPageUserTotal(query: UserQuery): AppResponse<PageTotal> {
  return httpService.post('/user/page/total', query)
}

// 导出一个异步函数，用于删除用户
export async function deleteUser(id: string): AppResponse<void> {
  return httpService.delete(`/user/${id}`)
}
