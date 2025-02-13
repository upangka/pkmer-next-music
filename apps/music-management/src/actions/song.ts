'use server'
import httpService from '@pkmer-music/management/service'
import { AppResponse } from '@pkmer/libs/http'
import type { SongQuery, SongQueryRes, PageTotal } from '@pkmer-music/management/types'

type State = {
  errors?: {}
  message?: string
}

export async function addSong(_preState: State, formData: FormData) {
  return {} as State
}

/**
 * 分页查询歌曲
 * @param query
 * @returns
 */
export async function pageQuerySong(query: SongQuery): AppResponse<SongQueryRes> {
  return httpService.post('/admin/song/page', query)
}

/**
 * 查询分页的总数
 * @param query
 * @returns
 */
export async function getSongPageQueryTotal(query: SongQuery): AppResponse<PageTotal> {
  return httpService.post('/admin/song/page/total', query)
}
