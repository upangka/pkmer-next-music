export type SexType = 'UNKNOWN' | 'MALE' | 'FEMALE'

export interface GetAllSongParams {
  name?: string
  sex?: SexType
}

export interface PageQueryRes<T> {
  currentPageNo: number
  list: T[]
  total: number
  totalPages: number
}

export interface Singer {
  id: string
  name: string
  sex: SexType
  pic: string
  birth: string
  location: string
  introduction: string
}

export type PageQuerySingerRes = PageQueryRes<Singer>
