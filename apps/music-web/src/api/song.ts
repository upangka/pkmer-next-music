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

type SexType = 'UNKNOWN' | 'MALE' | 'FEMALE'

interface GetAllSongParams {
  name?: string
  sex?: SexType
}

export const getAllSong = (data: GetAllSongParams = {}): AppResponse<any> => {
  return http.get('/singer/query', data)
}
