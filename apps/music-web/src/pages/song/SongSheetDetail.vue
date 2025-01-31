<script lang="ts">
import type { SongListDetail } from '@pkmer-music/web/types'
</script>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getSongListDetail } from '@pkmer-music/web/api'

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
    <section class="flex-1 border border-green-600">
      <div>
        <h1>简介</h1>
        <p v-html="songList?.introduction"></p>
      </div>
      <div>
        <table>
          <!-- 表格头 -->
          <tr>
            <th class="">序号</th>
            <th>歌曲</th>
            <th>歌手</th>
            <th>专辑</th>
          </tr>
          <tr v-for="(item, index) in songList?.songs" :key="item.id">
            <td>{{ index }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.introduction }}</td>
          </tr>
        </table>
      </div>
    </section>
    <!-- right end -->
  </section>
</template>
