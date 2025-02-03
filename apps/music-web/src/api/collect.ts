/**
 * 收藏相关API
 */
import type { AppResponse } from '@pkmer-music/web/types/http-base'
import http from './http'

export const isCollectSong = (songId: string): AppResponse<boolean> => {
  return http.get(`/collect/isCollectSong/${songId}`)
}
