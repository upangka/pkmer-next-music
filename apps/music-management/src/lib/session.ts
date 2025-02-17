'use server'

import { cookies } from 'next/headers'
import { redirect } from 'next/navigation'
import { verifySessionToken } from './jwt'
// 导出一个异步函数createSession，用于创建会话
export async function createSession(token: string) {
  // 调用cookies函数，获取cookieStore对象
  const cookieStore = await cookies()
  // 设置cookieStore对象的session属性，值为token，并设置httpOnly和path属性
  cookieStore.set('session', token, {
    httpOnly: true,
    path: '/'
  })
}

export async function deleteSession() {
  const cookieStore = await cookies()
  cookieStore.delete('session')
  redirect('/login')
}

export async function getCurrentUser() {
  const cookieStore = await cookies()
  const token = cookieStore.get('session')?.value
  if (token) {
    const payload = await verifySessionToken(token)
    return payload.usename as string
  }
  return ''
}
