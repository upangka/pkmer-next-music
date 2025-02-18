export type PageBase = {
  pageNo?: number
  pageSize?: number
}

export interface PageQueryRes<T> {
  currentPageNo: number
  list: T[]
  total: number
  totalPages: number
}

export type PageTotal = {
  total: number
  totalPages: number
}

export enum SexEnum {
  FEMALE = 0,
  MALE = 1,
  UNKNOWN = 2
}

export type Sex = SexEnum.FEMALE | SexEnum.MALE | SexEnum.UNKNOWN | undefined
