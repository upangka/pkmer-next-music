/**
 * 歌单相关API
 */
import http from './http'
import type {
  AppResponse,
  PageQuerySongListReq,
  PageQuerySongListRes,
  StyleType
} from '@pkmer-music/web//types'

/**
 * 分页查询歌单信息
 */

export const pageQuerySongList = (
  pageNo: number = 1,
  pageSize: number = 20,
  title: string = '',
  style: StyleType = ''
): AppResponse<PageQuerySongListRes> => {
  let req: PageQuerySongListReq = {
    pageNo,
    pageSize,
    title,
    style
  }
  return http.post('/songList/page', req)
}
