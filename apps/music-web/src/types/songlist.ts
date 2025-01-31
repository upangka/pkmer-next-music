import type { SongPlayListName } from '@pkmer-music/web/enums'

export interface PageQuerySongListReq {
  pageNo?: number
  pageSize?: number
  style?: SongPlayListName
  title?: string
}

export interface PageQuerySongListRes {
  id: string
  title: string
  pic: string
  styles: SongPlayListName[]
  introduction: string
}
