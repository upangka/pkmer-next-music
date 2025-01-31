export enum SongPlayListEnum {
  ALL = 0,
  Chinese,
  Cantonese, // 粤语
  EuropeAndAmerica, // 欧美
  JapanAndKorea, // 日韩
  LightMusic, // 轻音乐
  BGM,
  Instrumental //器乐
}

export type SongPlayListType = {
  name: string
  type: SongPlayListEnum
}

export const songPlayList = [
  {
    name: '全部',
    type: SongPlayListEnum.ALL
  },
  {
    name: '华语',
    type: SongPlayListEnum.Chinese
  },
  {
    name: '粤语',
    type: SongPlayListEnum.Cantonese
  },
  {
    name: '欧美',
    type: SongPlayListEnum.EuropeAndAmerica
  },
  {
    name: '日韩',
    type: SongPlayListEnum.JapanAndKorea
  },
  {
    name: '轻音乐',
    type: SongPlayListEnum.LightMusic
  },
  {
    name: '纯音乐',
    type: SongPlayListEnum.BGM
  },
  {
    name: '器乐',
    type: SongPlayListEnum.Instrumental
  }
] as const

export type SongPlayListName = (typeof songPlayList)[number]['name']
