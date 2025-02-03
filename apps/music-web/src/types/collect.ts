import type { PageQueryRes } from '.'

export interface CollectSong {
  songId: string
  name: string
  singerId: string
  singerName: string
  link: string
  picture: string
  introduce: string
}

interface SongData {
  id: number
  type: 'song' | 'songList'
  song: CollectSong
  songListId: null | number
  createTime: string
}

export type UserCollectRes = PageQueryRes<SongData>
