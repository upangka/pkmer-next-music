'use server'
import httpService from '@pkmer-music/management/service'
import { AppResponse } from '@pkmer/libs/http'
import type { SongListQuery, PageTotal, SongListQueryRes } from '@pkmer-music/management/types'

/**
 * 分页查询歌单
 * @param query
 * @returns
 */
export async function pageSongList(query: SongListQuery): AppResponse<SongListQueryRes> {
  return httpService.post('/songList/page', query)
}

/**
 * 根据条件查询总页数用于分页组件
 * @param query
 * @returns
 */
export async function getSongListPageTotal(query: SongListQuery): AppResponse<PageTotal> {
  return httpService.post('/songList/page/total', query)
}

/**
 * 删除歌单
 * @param id
 * @returns
 */
export async function deleteSongList(id: string): AppResponse<void> {
  return httpService.delete(`/songList/${id}`)
}
