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
const defaultPageQuery: PageQuerySongListReq = {
  pageNo: 1,
  pageSize: 20,
  title: '',
  style: ''
}

export const pageQuerySongList = (
  req: Partial<PageQuerySongListReq> = {}
): AppResponse<PageQuerySongListRes> => {
  const finalReq = { ...defaultPageQuery, ...req }
  return http.post('/songList/page', finalReq)
}
