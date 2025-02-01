<script lang="ts">
import type { SongListDetail } from '@pkmer-music/web/types'
</script>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getSongListDetail } from '@pkmer-music/web/api'
import { PkmerColumn, PkmerTable, PkmerRating } from '@pkmer-music-ui/vue'

const props = defineProps({
  id: String
})

const songList = ref<SongListDetail>()

onMounted(async () => {
  if (props.id) {
    const data = await getSongListDetail(props.id)
    songList.value = data
  }
})
</script>
<template>
  <section class="flex gap-5 border border-black py-5">
    <!-- left start -->
    <section
      class="flex w-fit flex-col items-center justify-center gap-9 border border-red-600 px-5 py-10"
    >
      <div class="h-[200px] w-[200px] overflow-hidden rounded-md shadow-xl">
        <img class="object-cover" :src="songList?.pic" alt="歌单封面" />
      </div>
      <p v-html="songList?.title" class="font-bold text-gray-500"></p>
    </section>
    <!-- left end -->
    <!-- right start -->

    <PkmerTable :data="songList?.songs">
      <PkmerColumn label="歌曲" prop="name" align="center" />
      <PkmerColumn label="歌手" prop="singerId" align="center" />
      <PkmerColumn label="专辑" prop="introduction" align="center" />
    </PkmerTable>
    <!-- right end -->
  </section>
</template>
