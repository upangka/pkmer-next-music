<script lang="ts">
import type { UserDetails, CollectSong } from '@pkmer-music/web/types'
</script>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getUserDetails, getUserCollectSong } from '@pkmer-music/web/api'
import { PkmerTable, PkmerColumn } from '@pkmer-music-ui/vue/table'
const user = ref<UserDetails>()
const songs = ref<CollectSong[]>()
onMounted(async () => {
  const data = await getUserDetails()
  user.value = data
})
onMounted(async () => {
  const data = await getUserCollectSong()

  const _songs = data.list.map(item => {
    return item.song
  })
  songs.value = _songs
})
</script>

<template>
  <section>
    <!-- 用户详情start -->
    <div class="relative h-[288px]">
      <div class="h-36 w-full bg-blue-500"></div>
      <div class="absolute top-1/2 flex -translate-y-1/2 gap-6 px-10">
        <div class="h-36 w-36 overflow-hidden rounded-full">
          <img :src="user?.avator" :alt="user?.username" class="object-cover" />
        </div>
        <div class="flex flex-col items-start justify-center gap-5">
          <h1 class="text-2xl font-bold">{{ user?.username }}</h1>
          <p class="text-gray-800">{{ user?.introduction }}</p>
        </div>
      </div>
    </div>
    <!-- 用户详情end -->
    <!-- 用户喜欢的歌曲start -->
    <div class="px-10 pb-10">
      <PkmerTable :data="songs || []">
        <PkmerColumn label="歌曲名" prop="name" align="center" />
        <PkmerColumn label="歌手" prop="singerName" align="center" />
        <PkmerColumn label="专辑" prop="introduction" align="center" />
      </PkmerTable>
    </div>

    <!-- 用户喜欢的歌曲end -->
  </section>
</template>
