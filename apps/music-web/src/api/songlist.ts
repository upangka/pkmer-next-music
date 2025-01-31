/**
 * 歌单相关API
 */
import http from './http'
import type {
  AppResponse,
  PageQuerySongListReq,
  PageQuerySongListRes
} from '@pkmer-music/web//types'

/**
 * 分页查询歌单信息
 */
export const pageQuerySongList = (
  req: PageQuerySongListReq = {
    pageNo: 1,
    pageSize: 20,
    title: '',
    style: '全部'
  }
): AppResponse<PageQuerySongListRes> => {
  return http.get('/songlist/pageQuerySongList', req)
}
