export interface PageQueryRes<T> {
  currentPageNo: number
  list: T[]
  total: number
  totalPages: number
}
