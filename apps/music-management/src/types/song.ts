import type { PageBase, PageQueryRes } from './base'

export type SongQuery = PageBase & {
  singerId: string
  name?: string
}

export interface SongDetailView {
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

export type SongQueryRes = PageQueryRes<SongDetailView>
