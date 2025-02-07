import type { PageBase, PageQueryRes } from './base'

export type UserQuery = PageBase & {
  username?: string
}

export interface UserDetail {
  id: string
  username: string
  sex: number
  phoneNum: string
  email: string
  birth: string // 或者可以使用 Date 类型，如果你打算将其转换为 Date 对象
  introduction: string
  location: string
  avator: string
  createTime: string // 或者可以使用 Date 类型
  updateTime: string // 或者可以使用 Date 类型
}

export type UserPageRes = PageQueryRes<UserDetail>
