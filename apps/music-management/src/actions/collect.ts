'use server'
import httpService from '@pkmer-music/management/service'
import type { AppResponse } from '@pkmer/libs/http'
import type { CollectQuery, PageTotal, CollectPageRes } from '@pkmer-music/management/types'

/**
 * 分页查询用户收藏列表
 * @param query
 * @returns
 */
export async function pageCollects(query: CollectQuery): AppResponse<CollectPageRes> {
  return httpService.post('/admin/collect/page', query)
}

/**
 * 删除指定用户收藏的歌曲
 * @param songId
 * @param userId
 * @returns
 */
export async function deleteCollect(songId: string, userId: string): AppResponse<void> {
  return httpService.delete(`/admin/collect/song`, {
    songId,
    userId
  })
}

export async function getPageCollectTotal(query: CollectQuery): AppResponse<PageTotal> {
  return httpService.post('/admin/collect/page/total', query)
}
