import type { PageBase, PageQueryRes } from './base'

export type SongListQuery = PageBase & {
  title?: string
  style?: string
}

export interface SongListDetailView {
  id: string // 歌单id
  title: string // 歌单标题
  pic: string // 歌单封面
  styles: string[] // 歌单风格
  introduction: string // 歌单介绍
  score: number // 歌单评分
  songs: SongListView[] // 歌单包含的歌曲
}

export interface SongListView {
  id: string // 歌曲id
  singerId: string // 歌手id
  singerName: string // 歌手名称
  name: string // 歌曲名称
  introduction: string // 歌曲简介
  pic: string // 歌曲封面
  url: string // 歌曲链接
}

export type SongListQueryRes = PageQueryRes<SongListDetailView>
