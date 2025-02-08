import { createHttpInstance } from '@pkmer/libs/http'
// 从环境变量中获取 API 基础路径
const isDev = import.meta.env.MODE === 'development'
const baseURL = isDev ? '/api' : import.meta.env.VITE_API_BASE_URL || ''

const getHttpInstance = createHttpInstance({
  baseURL,
  isServerSide: false,
  getToken: function () {
    return localStorage.getItem('token')
  }
})

const http = getHttpInstance()

export default http
