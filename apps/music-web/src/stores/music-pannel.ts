import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useMusicPannelStore = defineStore('music-pannel', () => {
  const showAssider = ref(false)

  return {
    showAssider
  }
})
