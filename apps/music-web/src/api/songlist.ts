/**
 * 歌单相关API
 */
import http from './http'
import type {
  AppResponse,
  PageQuerySongListReq,
  PageQuerySongListRes,
  SongListDetail,
  UserSongListRating,
  AddUserSongListRatingReq
} from '@pkmer-music/web/types'

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

/**
 * 获取歌单的详情
 */
export const getSongListDetail = (id: string): AppResponse<SongListDetail> => {
  return http.get(`/songList/${id}`)
}

/**
 * 获取当前用户的评分
 * @param id
 * @returns
 */
export const getUserSonglistScore = (id: string): AppResponse<UserSongListRating> => {
  return http.get(`/songList/user/rank/${id}`)
}

export const userAddScore = (data: AddUserSongListRatingReq): AppResponse<any> => {
  return http.post('/songList/addrank', data)
}
