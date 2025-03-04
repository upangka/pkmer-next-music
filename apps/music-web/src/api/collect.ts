/**
 * 收藏相关API
 */
import type { AppResponse, UserCollectRes } from '@pkmer-music/web/types'
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

// 导出一个函数getUserCollectSong，用于获取用户收藏的歌曲
export const getUserCollectSong = (): AppResponse<UserCollectRes> => {
  // 发送GET请求，获取收藏的歌曲
  return http.post('/collect/user/page')
}
