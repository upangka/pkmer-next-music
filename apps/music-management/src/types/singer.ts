import type { PageQueryRes, Sex } from './base'

export interface SongView {
  /** 歌曲id */
  id: string

  /** 歌曲名称 */
  name: string

  /** 歌曲简介 */
  introduction: string

  /** 歌曲封面 */
  picture: string

  /** 歌曲地址 */
  link: string
}

export interface Singer {
  /** 歌手id */
  id: string

  /** 歌手名称 */
  name: string

  /** 歌手性别 */
  sex: Sex // Enum example for gender

  /** 歌手图片 */
  pic: string

  /** 歌手生日 */
  birth: string // ISO 8601 string format for LocalDateTime

  /** 歌手地区 */
  location: string

  /** 歌手介绍 */
  introduction: string

  /** 歌手歌曲 */
  songs: SongView[]
}

export type SingerPageQueryRes = PageQueryRes<Singer>
