import { defineStore } from 'pinia'
import { ref, computed, onMounted } from 'vue'
import type { UserLogin } from '@pkmer-music/web/api/user'
export const useUserCenterStore = defineStore('user-center', () => {
  const username = ref('')
  const id = ref('')

  // 恢复持久化用户信息
  onMounted(() => {
    username.value = localStorage.getItem('username') || ''
    id.value = localStorage.getItem('id') || ''
  })

  const getUserEmail = computed(() => username.value)
  const isLogin = computed(() => !!username.value)

  function setUser(user: UserLogin) {
    username.value = user.username
    id.value = user.id
    persistenceUser()
  }

  /**
   * 持久化用户信息
   */
  async function persistenceUser() {
    await new Promise(() => {
      localStorage.setItem('username', username.value)
      localStorage.setItem('id', id.value)
    })
  }

  return {
    username,
    id,
    getUserEmail,
    setUser,
    isLogin
  }
})
