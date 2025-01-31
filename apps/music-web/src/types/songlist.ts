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
}
