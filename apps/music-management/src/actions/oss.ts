'use server'
import httpService from '@pkmer-music/management/service'
import type { AppResponse } from '@pkmer/libs/http'
import type { BigFileInitReq, FileInitView } from '@pkmer-music/management/types'

/**
 * 文件分片信息初始化
 * @param req
 * @returns
 */
export async function init(req: BigFileInitReq): AppResponse<FileInitView> {
  return httpService.post('/oss/bigfile/init', req)
}
