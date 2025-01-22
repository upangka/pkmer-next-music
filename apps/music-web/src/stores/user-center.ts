import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import type { UserLogin } from '@pkmer-music/web/api/user'
export const useUserCenterStore = defineStore('user-center', () => {
  const username = ref('')
  const id = ref('')

  const getUserEmail = computed(() => username.value)
  const isLogin = computed(() => !!username.value)

  function setUser(user: UserLogin) {
    console.log(user)
    username.value = user.username
    id.value = user.id
  }

  return {
    username,
    id,
    getUserEmail,
    setUser,
    isLogin
  }
})
