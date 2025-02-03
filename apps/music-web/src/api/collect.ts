/**
 * 收藏相关API
 */
import type { AppResponse } from '@pkmer-music/web/types/http-base'
import http from './http'

/**
 * 查询是否收藏过指定的歌曲
 * @param songId 歌曲id
 * @returns
 */
export const isCollectSong = (songId: string): AppResponse<boolean> => {
  return http.get(`/collect/isCollectSong/${songId}`)
}

/**
 * 收藏歌曲
 * @param songId 歌曲id
 * @returns
 */
export const collectSong = (songId: string): AppResponse<unknown> => {
  return http.post(`/collect/song/${songId}`)
}

/**
 * 取消收藏歌曲
 * @param songId 歌曲id
 * @returns
 */
export const cancelCollectSong = (songId: string): AppResponse<unknown> => {
  return http.delete(`/collect/song/${songId}`)
}
