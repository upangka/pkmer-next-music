'use server'
import httpService from '@pkmer-music/management/service'
import type { AppResponse } from '@pkmer/libs/http'
import type { BigFileInitReq, FileInitView } from '@pkmer-music/management/types'

export async function init(req: BigFileInitReq): AppResponse<FileInitView> {
  return httpService.post('/oss//bigfile/init', req)
}
