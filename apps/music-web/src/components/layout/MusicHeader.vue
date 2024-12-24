<script lang="ts" setup>
import { ref } from 'vue'
const inputFocusStatus = ref(true)
</script>

<template>
  <div class="flex items-center justify-between px-10 py-5">
    <!-- 左边start -->
    <section class="flex items-center justify-start gap-3">
      <iconify-icon icon="tdesign:music-rectangle-add-filled" height="36"></iconify-icon>
      <h1 class="text-xl font-bold">Pkmer-Next-Music</h1>
      <nav>
        <ul class="flex items-center justify-start gap-6">
          <li>首页</li>
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
      </nav>
    </section>
    <!-- 左边end -->
    <!-- 右边start -->
    <section class="flex justify-start gap-5">
      <span>登录</span>
      <span>注册</span>
    </section>
    <!-- 右边end -->
  </div>
</template>
