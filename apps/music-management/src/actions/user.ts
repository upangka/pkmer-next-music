'use server'
import httpService from '@pkmer-music/management/service'

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
