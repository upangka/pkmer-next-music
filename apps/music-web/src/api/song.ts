import http from './http'
import type { AppResponse, SongDetail } from '@pkmer-music/web/types'

/**
 *
 * @param id 歌曲id
 * @returns
 */
export const getSongDetail = (id: string): AppResponse<SongDetail> => {
  return http.get(`/song/${id}`)
}
