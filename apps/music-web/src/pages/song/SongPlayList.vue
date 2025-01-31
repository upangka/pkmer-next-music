<script lang="ts">
import type { PageQuerySongListRes } from '@pkmer-music/web/types'
import type { MusicCard } from '@pkmer-music/web/types'
</script>

<script lang="ts" setup>
import { onMounted, ref, computed } from 'vue'
import { PlayList } from '@pkmer-music/web/components'
import {
  PkmerNavigationIndicator,
  PkmerNavigationItem,
  PkmerNavigationList,
  PkmerNavigationRoot
} from '@pkmer-music-ui/vue/navigation'
import { songPlayList } from '@pkmer-music/web/enums'
import { pageQuerySongList } from '@pkmer-music/web/api'
// 模拟数据
// import songs from '@pkmer-music/web/assets/songs.json'

const songs = ref<PageQuerySongListRes>()

onMounted(async () => {
  const data = await pageQuerySongList()
  songs.value = data
})

const playList = computed<MusicCard[]>(() => {
  return (
    songs.value?.list.map(item => ({
      imgurl: item.pic,
      dissname: item.title
    })) || []
  )
})
</script>

<template>
  <div class="song-play-list__container">
    <PkmerNavigationRoot>
      <PkmerNavigationList :show-border="false" :item-gap="10">
        <template v-for="songPlay in songPlayList" :key="songPlay.type">
          <PkmerNavigationItem>
            {{ songPlay.name }}
          </PkmerNavigationItem>
        </template>
      </PkmerNavigationList>
      <PkmerNavigationIndicator color="#eab308" />
    </PkmerNavigationRoot>
    <!-- 歌单列表展示start -->
    <section class="mt-3">
      <PlayList path="/xxx" :play-list="playList" />
    </section>
    <!-- 歌单列表展示end -->
  </div>
</template>

<style lang="scss" scoped>
@use '@pkmer-music/web/assets/styles/mixins' as PkmerMixin;

.song-play-list__container {
  @include PkmerMixin.play-list__container();
  margin-top: 10px;
}
</style>
