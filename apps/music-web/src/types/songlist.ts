import type { SongPlayListName } from '@pkmer-music/web/enums'

export type StyleType = '' | SongPlayListName
export interface PageQuerySongListReq {
  pageNo: number
  pageSize: number
  style: StyleType
  title: string
}

export interface PageQuerySongListRes {
  currentPageNo: number
  list: SongList[]
  total: number
  totalPages: number
}

export interface SongList {
  id: string
  title: string
  pic: string
  styles: SongPlayListName[]
  introduction: string
  score: number
}

/**
 * 歌曲详情
 */
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
   * 歌手名称
   */
  singerName: string

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

export type SongListDetail = SongList & {
  songs: SongDetail[]
}

/**
 * 用户歌单评分
 */
export interface UserSongListRating {
  id: number
  songListId: number
  songListName: string
  consumerId: number
  score: number
}
