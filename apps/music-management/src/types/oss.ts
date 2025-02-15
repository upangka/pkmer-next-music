/**
 * minio 文件分片初始化
 */
export interface BigFileInitReq {
  /**
   * 文件md5
   */
  fileMd5: string

  /**
   * 文件名称
   */
  fullFileName: string

  /**
   * 文件长度
   * @minimum 1 文件大小不能小于1
   */
  fileSize: number
}

/**
 * 文件分片初始化信息
 */
export interface FileInitView {
  id: string
  fileKey: string
  fileMd5: string
  fileName: string
  fileMimeType: string
  fileSuffix: string
  fileSize: number
  bucket: string
  bucketPath: string
  uploadId: string
  finished: boolean
  partNumber: number
  createTime: Date
  parts: Part[]
}

/**
 * 分片信息
 */
export interface Part {
  uploadId: string
  uploadUrl: string
  partNumber: number
  shardingStart: number
  shardingEnd: number
  uploaded: boolean
  etag: string
}
