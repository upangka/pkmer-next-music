'use server'
import httpService from '@pkmer-music/management/service'
import type { AppResponse } from '@pkmer/libs/http'
import type { BigFileInitReq, FileInitView, MergeFileResult } from '@pkmer-music/management/types'

/**
 * 文件分片信息初始化
 * @param req
 * @returns
 */
export async function init(req: BigFileInitReq): AppResponse<FileInitView> {
  return httpService.post('/oss/bigfile/init', req)
}

/**
 * 通知后端合并分片信息
 * @param fileMd5
 * @param parts
 * @returns
 */
export async function mergeParts(fileMd5: string, parts: string[]): AppResponse<MergeFileResult> {
  return httpService.post(`/oss/bigfile/merge?fileMd5=${fileMd5}`, parts)
}
