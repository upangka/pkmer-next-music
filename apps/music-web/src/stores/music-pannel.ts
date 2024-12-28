import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { songs } from '@pkmer-music/web/assets/audio'

export const useMusicPannelStore = defineStore('music-pannel', () => {
  // state
  const showAssider = ref(false)
  const currentPlayingSongId = ref<number | null>(null)

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
   * 这里返回的songs并不是state，而是getter
   */
  return {
    showAssider,
    songs,
    currentPlayingSongId,
    currentPlayingIndex,
    play
  }
})
