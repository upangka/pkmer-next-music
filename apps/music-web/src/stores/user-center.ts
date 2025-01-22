import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
export const useUserCenter = defineStore('user-center', () => {
  const username = ref('')

  const getUsername = computed(() => username.value)

  return {
    username,
    getUsername
  }
})
