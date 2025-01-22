import type { AxiosRequestConfig } from 'axios'
export type Data = Record<string, any>

export interface AppBaseResponse<T = unknown> {
  code: number
  msg: string
  data: T
}

export type AppResponse<T> = Promise<T>

export interface AppHTTP {
  get<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
  post<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
  put<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
  delete<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T>
}
