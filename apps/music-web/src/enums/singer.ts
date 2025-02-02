export enum SingerType {
  ALL = '',
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  Group = 'UNKNOWN' // TODO 乐队/组合 目前用unknown代替
}
export type SingerStyle = {
  name: string
  type: SingerType
}

export const singerStyles: SingerStyle[] = [
  {
    name: '全部',
    type: SingerType.ALL
  },
  {
    name: '男歌手',
    type: SingerType.MALE
  },
  {
    name: '女歌手',
    type: SingerType.FEMALE
  },
  {
    name: '乐队/组合',
    type: SingerType.Group
  }
]
