import type { PageQueryRes, Sex } from './base'
import type { Prettify } from './type-util'
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

export type Singer = Prettify<
  Omit<SingerBase, 'sex'> & {
    /** 歌手id */
    id: string

    /** 歌手歌曲 */
    songs: SongView[]
    /**
     * todo 兼容之前写的代码歌手性别
     */
    sex: string
  }
>

/**
 * 歌手基础信息
 */
export interface SingerBase {
  /**
   * 歌手名称
   */
  name: string
  /**
   * 歌手性别
   */
  sex: Sex
  /**
   * 歌手图片
   */
  pic: string

  /**
   * 歌手生日
   */
  birth: string

  /**
   * 歌手地区
   */
  location: string

  /**
   * 歌手简介
   */
  introduction: string
}

export type SingerPageQueryRes = PageQueryRes<Singer>
