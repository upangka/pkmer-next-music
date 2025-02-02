import http from './http'
import type { AppResponse, Singer } from '@pkmer-music/web/types'

/**
 * 获取歌手详情
 * @param id 歌手id
 * @returns
 */
export const getSingerDetail = (id: string): AppResponse<Singer> => {
  return http.get(`/singer/${id}`)
}
