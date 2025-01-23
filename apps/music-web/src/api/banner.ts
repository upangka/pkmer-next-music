import type { AppResponse } from '@pkmer-music/web/types/http-base'
import http from './http'
export interface Banner {
  id: string
  url: string
}
/**
 * 用于获取所有轮播图
 * @returns
 */
export const getAllBanners = (): AppResponse<Banner[]> => {
  return http.get('/banner')
}
