import http from './http'
import type { AppResponse } from '@pkmer-music/web//types/http-base'

export interface SongDetail {
  /**
   * 歌曲id
   */
  id: string

  /**
   * 歌手id
   */
  singerId: string

  /**
   * 歌曲名称
   */
  name: string

  /**
   * 歌曲简介
   */
  introduction: string

  /**
   * 歌曲封面
   */
  pic: string

  /**
   * 歌曲地址
   */
  url: string

  /**
   * 歌词
   */
  lyric: string
}

export const getSongDetail = (id: string): AppResponse<SongDetail> => {
  return http.get(`/song/${id}`)
}
