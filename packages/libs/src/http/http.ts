import axios from 'axios'
import type { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios'
import type { AppHTTP, Data, AppBaseResponse, AppResponse } from './http-base'

export function createHttpInstance(baseURL: string) {
  let http: AppHTTP | null = null

  // 为了防止rollup打包时tree-shaking，将函数放在闭包中
  return function getHttpInstance() {
    if (http !== null) {
      return http
    }
    // 创建 Axios 实例
    const axiosInstance: AxiosInstance = axios.create({
      baseURL,
      timeout: 10000
    })

    // 请求拦截器
    axiosInstance.interceptors.request.use(
      config => {
        // 从本地存储中获取 token
        const token = localStorage.getItem('token')
        console.log({ token })
        if (token) {
          // 如果存在 token，则将其添加到请求头中
          config.headers.Authorization = `Bear ${token}`
        }
        return config
      },
      error => Promise.reject(error) // 请求错误时，直接返回错误信息
    )

    // 响应拦截器
    axiosInstance.interceptors.response.use(
      (response: AxiosResponse) => {
        return response
      }, // 直接返回响应数据
      error => Promise.reject(error) // 响应错误时，直接返回错误信息
    )

    // 通用的请求函数
    http = {
      /**
       * 发送 GET 请求
       * @param url 请求的 URL
       * @param data 请求的参数
       * @param config 额外的 Axios 配置
       * @returns 返回一个 Promise，包含响应数据
       */
      async get<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T> {
        try {
          const response = await axiosInstance.get<AppBaseResponse<T>>(url, {
            params: data,
            ...config
          })
          return response.data.data
        } catch (error) {
          if (axios.isAxiosError(error)) {
            console.log(error.message) // 打印错误信息
          }
          throw error // 重新抛出错误，以便调用者可以处理它
        }
      },

      /**
       * 发送 POST 请求
       * @param url 请求的 URL
       * @param data 请求的数据
       * @param config 额外的 Axios 配置
       * @returns 返回一个 Promise，包含响应数据
       */
      async post<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T> {
        try {
          const response = await axiosInstance.post<AppBaseResponse<T>>(url, data, config)
          return response.data.data
        } catch (error) {
          if (axios.isAxiosError(error)) {
            console.log(error.message) // 打印错误信息
          }
          throw error // 重新抛出错误，以便调用者可以处理它
        }
      },

      /**
       * 发送 PUT 请求
       * @param url 请求的 URL
       * @param data 请求的数据
       * @param config 额外的 Axios 配置
       * @returns 返回一个 Promise，包含响应数据
       */
      async put<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T> {
        try {
          const response = await axiosInstance.put<AppBaseResponse<T>>(url, data, config)
          return response.data.data
        } catch (error) {
          if (axios.isAxiosError(error)) {
            console.log(error.message) // 打印错误信息
          }
          throw error // 重新抛出错误，以便调用者可以处理它
        }
      },

      /**
       * 发送 DELETE 请求
       * @param url 请求的 URL
       * @param data 请求的参数
       * @param config 额外的 Axios 配置
       * @returns 返回一个 Promise，包含响应数据
       */
      async delete<T>(url: string, data?: Data, config?: AxiosRequestConfig): AppResponse<T> {
        try {
          const response = await axiosInstance.delete<AppBaseResponse<T>>(url, {
            params: data,
            ...config
          })
          return response.data.data
        } catch (error) {
          if (axios.isAxiosError(error)) {
            console.log(error.message) // 打印错误信息
          }
          throw error // 重新抛出错误，以便调用者可以处理它
        }
      }
    }
    return http
  }
}
