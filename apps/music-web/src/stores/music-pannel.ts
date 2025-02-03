import { defineStore } from 'pinia'
import { ref, computed, reactive, watch } from 'vue'
import { songs } from '@pkmer-music/web/assets/audio'
import { isCollectSong } from '@pkmer-music/web/api'
import type { Song } from '@pkmer-music/web/types'
export const useMusicPannelStore = defineStore('music-pannel', () => {
  // state
  const showAssider = ref(false)
  const currentPlayingSongId = ref<number | null>(null)
  const songList = ref<Song[]>(songs)

  const audioRef = ref<HTMLAudioElement | null>(null)

  const isCollectCurrentSong = ref(false)

  // 当切换歌曲的时候，进行查询用户是否收藏了当前歌曲
  watch(
    () => currentPlayingSongId.value,
    async newSongId => {
      try {
        isCollectCurrentSong.value = false
        if (newSongId !== null) {
          const r = await isCollectSong(newSongId.toString())
          console.log({ r })
          isCollectCurrentSong.value = r
        }
      } catch (error) {
        console.log(error)
        isCollectCurrentSong.value = false
      }
    }
  )

  /**
   * 音乐栏播放状态
   */
  const playBayStatus = reactive({
    isPlaying: false,
    showMusicInfo: false
  })

  // getters
  const currentPlayingIndex = computed(() => {
    if (currentPlayingSongId.value === null) return 0
    return songs.findIndex(song => song.id === currentPlayingSongId.value)
  })

  // actions
  function play(songId: number) {
    currentPlayingSongId.value = songId
  }

  /**
   * 增加播放的歌曲并播放
   * @param song
   */
  function addSongAndPlay(song: Song) {
    songList.value.push(song)
    play(song.id)
    showMusicBar()
  }

  function showMusicBar() {
    playBayStatus.isPlaying = true
    playBayStatus.showMusicInfo = true
  }

  /**
   * 这里返回的songs并不是state，而是getter
   */
  return {
    showAssider,
    songs: songList,
    currentPlayingSongId,
    currentPlayingIndex,
    play,
    audioRef,
    addSongAndPlay,
    playBayStatus,
    isCollectCurrentSong
  }
})
