<script lang="ts">
import type { Singer, Song, SingerSong } from '@pkmer-music/web/types'
interface Props {
  singerId: string
}
</script>
<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { getSingerDetail } from '@pkmer-music/web/api'
import { PkmerTable, PkmerColumn } from '@pkmer-music-ui/vue/table'
import { useMusicPannelStore } from '@pkmer-music/web/stores'

const props = defineProps<Props>()
const musicPannelStore = useMusicPannelStore()
const singer = ref<Singer>()
onMounted(async () => {
  const data = await getSingerDetail(props.singerId)

  if (data) {
    const singerName = data.name
    data.songs.forEach(item => {
      item.singerName = singerName
      return item
    })

    singer.value = data
  }
})

function handleTableRowClick(row: unknown) {
  if (row) {
    const singerSong = row as SingerSong
    musicPannelStore.addSongAndPlay({
      id: +singerSong.id,
      link: singerSong.link,
      name: singerSong.name,
      picture: singerSong.picture
    })
  }
}
</script>
<template>
  <section class="flex gap-6 p-5">
    <!-- 左边start -->
    <div class="flex flex-col gap-5 p-5">
      <div class="h-[200px] w-[200px] overflow-hidden rounded-md shadow-xl">
        <img :src="singer?.pic" :alt="singer?.name" />
      </div>
      <!-- 用户详情 -->
      <div class="flex flex-col gap-3">
        <h1 class="text-xl font-bold">基本资料</h1>
        <ul class="flex flex-col gap-1">
          <li>
            <span class="singer-item">性别：</span>{{ singer?.sex === 'FEMALE' ? '女' : '男' }}
          </li>
          <li><span class="singer-item">生日：</span>{{ singer?.birth.split('T')[0] }}</li>
          <li><span class="singer-item">故乡：</span> {{ singer?.location }}</li>
        </ul>
      </div>
    </div>
    <!-- 左边end -->
    <!-- 右边start -->
    <div class="flex flex-1 flex-col gap-5 p-5">
      <div>
        <h1 class="py-3 text-2xl font-bold">简介</h1>
        <p class="text-sm text-gray-500">{{ singer?.introduction }}</p>
      </div>

      <!-- 列表start -->
      <PkmerTable :data="singer?.songs || []" @row-click="handleTableRowClick">
        <PkmerColumn label="歌曲名" prop="name" align="center" />
        <PkmerColumn label="歌手" prop="singerName" align="center" />
        <PkmerColumn label="专辑" prop="introduction" align="center" />
      </PkmerTable>
      <!-- 列表end -->
    </div>
    <!-- 右边end -->
  </section>
</template>
<style lang="scss" scoped>
li {
  white-space: nowrap;
  .singer-item {
    color: gray;
    font-size: 18px;
    margin-right: 5px;
  }
}
</style>
