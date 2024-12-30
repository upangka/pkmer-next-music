<script lang="ts" setup>
import { ref } from "vue"
import { RouterLink } from 'vue-router'
import { SearchInput } from '@pkmer-music/web/components';
import { NavigationRoot, NavigationList, NavigationItem, NavigationIndicator, PkmerIcon } from "@pkmer-music-ui/vue"

const showLoginIndicator = ref(false)
</script>

<template>
  <div class="header-container flex items-center justify-between px-10 py-5">
    <!-- 左边start -->
    <section class="flex items-center justify-evenly w-full">
      <div class="flex items-center justify-start gap-3">
        <PkmerIcon icon="tdesign:music-rectangle-add-filled" :height="36" :width="36" />
        <h1 class="text-xl font-bold">Pkmer-Next-Music</h1>
      </div>
      <div @click="showLoginIndicator = false">
        <NavigationRoot>
          <NavigationList :item-gap="30">
            <NavigationItem>
              <RouterLink to="/">首页</RouterLink>
            </NavigationItem>
            <NavigationItem>歌单</NavigationItem>
            <NavigationItem>
              <RouterLink to="/singers">歌手</RouterLink>
            </NavigationItem>
            <NavigationItem>
              <SearchInput />
            </NavigationItem>
          </NavigationList>
          <NavigationIndicator color="black" v-show="!showLoginIndicator" />
        </NavigationRoot>
      </div>

      <!-- 右边start -->
      <NavigationRoot>
        <NavigationList :item-gap="0">
          <NavigationItem>
            <section class="flex justify-start gap-5" @click="showLoginIndicator = !showLoginIndicator">
              <RouterLink to="/login">登录</RouterLink>
            </section>
          </NavigationItem>
        </NavigationList>
        <NavigationIndicator color="black" v-show="showLoginIndicator" />
      </NavigationRoot>


      <!-- 右边end -->
    </section>
    <!-- 左边end -->

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
  min-width: 1460px;
}
</style>
