<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getSongDetail, type SongDetail } from '@pkmer-music/web/api'
const songDetail = ref<SongDetail>()
onMounted(async () => {
  const data = await getSongDetail(Number(1).toString())
  songDetail.value = data
})
</script>
<template>
  <section class="border-2 border-black">
    <ul class="mx-auto w-fit p-5">
      <li v-for="(item, index) in songDetail?.lyric.split('\n')" :key="index">{{ item }}</li>
    </ul>
  </section>
</template>
