'use server'
import httpService from '@pkmer-music/management/service'
import { AppResponse } from '@pkmer/libs/http'
import type { Sex, PageTotal, SingerPageQueryRes, SingerBase } from '@pkmer-music/management/types'
export async function deleteSinger(id: string): AppResponse<void> {
  return httpService.delete(`/singer/${id}`)
}

export async function pageSinger(query: {
  sex?: Sex
  name?: string
  pageNo?: number
  pageSize?: number
}): AppResponse<SingerPageQueryRes> {
  return httpService.get('/singer/page', query)
}

export async function getSingerPageTotal(query: {
  sex?: Sex
  name?: string
  pageSize?: number
}): AppResponse<PageTotal> {
  return httpService.get('/singer/page/total', query)
}

/**
 * 新加歌手
 * @param singer 歌手信息
 * @returns
 */
export async function addSinger(singer: SingerBase): AppResponse<void> {
  return httpService.post('/singer/add', singer)
}
