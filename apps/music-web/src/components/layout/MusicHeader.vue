<script lang="ts" setup>
// import { ref } from 'vue'
import { RouterLink } from 'vue-router'
// const inputFocusStatus = ref(true)

import { NavigationRoot, NavigationList, NavigationItem, NavigationIndicator } from "@pkmer-music-ui/vue"
// import { NavigationRoot } from "@pkmer-music-ui/vue/navigaton"
</script>

<template>
  <div class="header-container flex items-center justify-between px-10 py-5">
    <!-- 左边start -->
    <section class="flex items-center justify-start gap-3">
      <iconify-icon icon="tdesign:music-rectangle-add-filled" height="36"></iconify-icon>
      <h1 class="text-xl font-bold">Pkmer-Next-Music</h1>

      <NavigationRoot>
        <NavigationList>
          <NavigationItem>首页</NavigationItem>
          <NavigationItem>歌单</NavigationItem>
          <NavigationItem>歌手杰</NavigationItem>
        </NavigationList>
        <NavigationIndicator />
      </NavigationRoot>

      <!-- <nav>
        <ul class="flex items-center justify-start gap-6">
          <li>
            <RouterLink to="/">首页</RouterLink>
          </li>
          <li>歌单</li>
          <li>歌手</li>
          <li class="relative">
            <iconify-icon v-show="inputFocusStatus" icon="iconamoon:search-thin"
              class="text-1xl absolute left-1 top-1/2 -translate-y-1/2 text-slate-500"></iconify-icon>
            <input @focus="inputFocusStatus = false" @blur="inputFocusStatus = true" type="text"
              placeholder="search something..."
              class="w-80 rounded-md border border-gray-600 p-0.5 pl-5 focus:outline-none" />
          </li>
        </ul>
      </nav> -->
    </section>
    <!-- 左边end -->
    <!-- 右边start -->
    <section class="flex justify-start gap-5">
      <RouterLink to="/login">登录</RouterLink>
    </section>
    <!-- 右边end -->
  </div>
</template>

<style lang="scss" scoped>
/* @import "@pkmer-music/web/assets/styles/__variables.scss"; */
@use "@pkmer-music/web/assets/styles/__variables.scss" as *;

.header-container {
  height: $header-height;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: $music-header-z-index;
  background-color: white;
}
</style>
