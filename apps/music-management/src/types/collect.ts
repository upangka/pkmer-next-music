import type { PageBase, PageQueryRes } from '@pkmer-music/management/types'

export type CollectQuery = PageBase & {
  userId: string
  songName?: string
}

export interface CollectSongDto {
  id: string
  userId: number
  type: string
  song: SongDto
  songId: number
  createTime: string
}

export interface SongDto {
  id: string
  name: string
  singerId: string
  singerName: string
  url: string
  pic: string
  introduction: string
}

export type CollectPageRes = PageQueryRes<CollectSongDto>
