'use server'
import httpService from '@pkmer-music/management/service'
import { AppResponse } from '@pkmer/libs/http'
import type { SongQuery, SongQueryRes, PageTotal } from '@pkmer-music/management/types'

type State = {
  errors?: {}
  message?: string
}

/**
 * 更新歌曲信息
 * @param _preState
 * @param formData
 * @returns
 */
export async function updateSong(_preState: State, formData: FormData) {
  try {
    console.log(formData)
    await httpService.post('/song/update', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    return {} as State
  } catch (err) {
    return { message: '更新歌曲信息失败' } as State
  }
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
