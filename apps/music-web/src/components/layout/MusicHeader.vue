<script lang="ts" setup>
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
import { RouterLink, useRouter } from 'vue-router'
import { SearchInput } from '@pkmer-music/web/components'
import {
  PkmerNavigationRoot,
  PkmerNavigationList,
  PkmerNavigationItem,
  PkmerNavigationIndicator,
  PkmerIcon,
  PkmerAvatarFallback,
  PkmerAvatarRoot,
  PkmerAvatarImage,
  PkmerDropdownMenu,
  PkmerDropdownMenuContent,
  PkmerDropdownMenuTrigger,
  PkmerDropdownMenuItem
} from '@pkmer-music-ui/vue'

import { useUserCenterStore } from '@pkmer-music/web/stores'
const router = useRouter()

const showLoginIndicator = ref(false)
const { isLogin } = storeToRefs(useUserCenterStore())

function handleLogin() {
  showLoginIndicator.value = true
  router.push('/login')
}
</script>

<template>
  <div class="header-container flex items-center justify-between px-10 py-5">
    <!-- 左边start -->
    <section class="flex w-full items-center justify-evenly">
      <div class="flex items-center justify-start gap-3">
        <PkmerIcon icon="tdesign:music-rectangle-add-filled" :height="36" :width="36" />
        <h1 class="text-xl font-bold">Pkmer-Next-Music</h1>
      </div>
      <div @click="showLoginIndicator = false">
        <PkmerNavigationRoot>
          <PkmerNavigationList :item-gap="30">
            <PkmerNavigationItem>
              <RouterLink to="/">首页</RouterLink>
            </PkmerNavigationItem>
            <PkmerNavigationItem>
              <RouterLink to="/songs">歌单</RouterLink>
            </PkmerNavigationItem>
            <PkmerNavigationItem>
              <RouterLink to="/singers">歌手</RouterLink>
            </PkmerNavigationItem>
            <PkmerNavigationItem>
              <SearchInput />
            </PkmerNavigationItem>
          </PkmerNavigationList>
          <PkmerNavigationIndicator color="black" v-show="!showLoginIndicator" />
        </PkmerNavigationRoot>
      </div>

      <!-- 右边start -->

      <section>
        <PkmerDropdownMenu v-if="isLogin">
          <PkmerDropdownMenuTrigger>
            <PkmerAvatarRoot>
              <PkmerAvatarFallback
                class="flex h-[50px] w-[50px] items-center justify-center rounded-full bg-gray-500 text-lg font-bold text-white"
                >pk</PkmerAvatarFallback
              >
              <PkmerAvatarImage
                class="h-[50px] w-[50px] rounded-full object-fill"
                src="https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg?param=180y180"
              />
            </PkmerAvatarRoot>
          </PkmerDropdownMenuTrigger>
          <PkmerDropdownMenuContent>
            <PkmerDropdownMenuItem>
              <RouterLink to="/personal">个人中心</RouterLink>
            </PkmerDropdownMenuItem>

            <PkmerDropdownMenuItem>
              <RouterLink to="/setting">设置</RouterLink>
            </PkmerDropdownMenuItem>
            <PkmerDropdownMenuItem> 退出登录 </PkmerDropdownMenuItem>
          </PkmerDropdownMenuContent>
        </PkmerDropdownMenu>

        <PkmerNavigationRoot v-else>
          <PkmerNavigationList :item-gap="0">
            <PkmerNavigationItem>
              <section class="flex justify-start gap-5" @click="handleLogin">
                <RouterLink to="/login">登录</RouterLink>
              </section>
            </PkmerNavigationItem>
          </PkmerNavigationList>
          <PkmerNavigationIndicator color="black" v-show="showLoginIndicator" />
        </PkmerNavigationRoot>
      </section>

      <!-- 右边end -->
    </section>
    <!-- 左边end -->
  </div>
</template>

<style lang="scss" scoped>
/* @import "@pkmer-music/web/assets/styles/__variables.scss"; */
@use '@pkmer-music/web/assets/styles/__variables.scss' as *;

.header-container {
  height: $header-height;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: $music-header-z-index;
  background-color: white;
  min-width: 1460px;
  box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
}
</style>
