export enum SingerType {
  ALL = -1,
  Male,
  Female,
  Group
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
    type: SingerType.Male
  },
  {
    name: '女歌手',
    type: SingerType.Female
  },
  {
    name: '乐队/组合',
    type: SingerType.Group
  }
]
