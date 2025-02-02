import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { songs } from '@pkmer-music/web/assets/audio'
import type { Song } from '@pkmer-music/web/types'
export const useMusicPannelStore = defineStore('music-pannel', () => {
  // state
  const showAssider = ref(false)
  const currentPlayingSongId = ref<number | null>(null)
  const songList = ref<Song[]>(songs)

  const audioRef = ref<HTMLAudioElement | null>(null)

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
    addSongAndPlay
  }
})
