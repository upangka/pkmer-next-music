import http from './http'
import type {
  AppResponse,
  GetAllSongParams,
  PageQuerySingerRes,
  SongDetail
} from '@pkmer-music/web/types'

/**
 *
 * @param id 歌曲id
 * @returns
 */
export const getSongDetail = (id: string): AppResponse<SongDetail> => {
  return http.get(`/song/${id}`)
}

export const getAllSong = (data: GetAllSongParams = {}): AppResponse<PageQuerySingerRes> => {
  return http.get('/singer/page', data)
}
