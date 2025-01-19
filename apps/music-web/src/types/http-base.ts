import type { AxiosRequestConfig } from 'axios'
export type Data = Record<string, any>

export type AppResponse<T> = Promise<AppBaseResponse<T>>

export interface AppHTTP {
  get<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
  post<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
}

export interface AppBaseResponse<T = unknown> {
  code: number
  msg: string
  data: T
}

export interface AppHTTP {
  get<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
  post<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
}
