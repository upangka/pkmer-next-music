import song1 from './黄昏(Dj版) - 刘汉成.mp3'
import song2 from './Phai Dấu Cuộc Tình (Remix Ver.) - Vicky Nhung.mp3'
import song3 from './Tình Ta Hai Ngã (Divided Love) - Hard Vina - Kickcheeze.mp3'
import song4 from './XEM NHƯ ANH CHẲNG MAY (Remix) - Ưng Hoàng Phúc.mp3'
import song5 from './月亮里的阿妹 (DJ村长版DJ版) - DJ村长.mp3'

interface Song {
  link: string
  name: string
  picture: string
}

export const songs = [
  {
    link: song1,
    name: '黄昏(Dj版) - 刘汉成',
    picture:
      'http://qpic.y.qq.com/music_cover/QoBD9LhzJusrvSqM8DM9aLPInhPqynIvqXyzNKTny6aMUU6N0EFUjA/300?n=1'
  },
  {
    link: song2,
    name: 'Phai Dấu Cuộc Tình (Remix Ver.) - Vicky Nhung',
    picture:
      'https://music-file.y.qq.com/songlist/u/oiSq7K4AowoqNn/743b/d7d68662155e54ad1a22821e6891368a807bb6c8_247193.jpg?imageView2/4/w/300/h/300'
  },
  {
    link: song3,
    name: 'Tình Ta Hai Ngã (Divided Love) - Hard Vina - Kickcheeze',
    picture:
      'http://qpic.y.qq.com/music_cover/Kfat8dubG9jd2J815HySpm8aUfYTEGQfkd4Wcp46cx1ibaE9bdwr41g/300?n=1'
  },
  {
    link: song4,
    name: 'XEM NHƯ ANH CHẲNG MAY (Remix) - Ưng Hoàng Phúc',
    picture:
      'https://music-file.y.qq.com/songlist/u/oKo57w6z7evq7v/6bc3/a859a02adf75ed55486c933882fcbf79cd322163_2399f6.jpg?imageView2/4/w/300/h/300'
  },
  {
    link: song5,
    name: '月亮里的阿妹 (DJ村长版DJ版) - DJ村长',
    picture:
      'http://qpic.y.qq.com/music_cover/SFAOAt53mBM83vE3VTTZzU6B8lQntKRw4iaqUbZ541pCx5piaic5ePKfA/300?n=1'
  }
] satisfies Song[]
