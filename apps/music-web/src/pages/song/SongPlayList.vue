<script lang="ts">
import type { PageQuerySongListRes } from '@pkmer-music/web/types'
import type { MusicCard, StyleType } from '@pkmer-music/web/types'
</script>

<script lang="ts" setup>
import { useRouter } from 'vue-router'
import { onMounted, ref, computed, provide } from 'vue'
import { PlayList } from '@pkmer-music/web/components'
import { musicCardKey } from '@pkmer-music/web/components/layout/musicCardContext'
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

const router = useRouter()
const songs = ref<PageQuerySongListRes>()
const styleName = ref<StyleType>('')

provide(musicCardKey, {
  onClick: handleMusicCardClick
})

onMounted(getSongList)

const playList = computed<MusicCard[]>(() => {
  return (
    songs.value?.list.map(item => ({
      id: item.id,
      imgurl: item.pic,
      dissname: item.title
    })) || []
  )
})

async function getSongList() {
  const data = await pageQuerySongList({ style: styleName.value })
  songs.value = data
}

function handleStyleChange(target: HTMLElement) {
  let targetStyle: StyleType = (target.textContent as unknown as StyleType) || ''
  if (targetStyle === '全部') {
    targetStyle = ''
  }
  styleName.value = targetStyle
  getSongList()
}

function handleMusicCardClick(card: MusicCard) {
  router.push({
    path: `/songsheet/${card.id}`
  })
}
</script>

<template>
  <div class="song-play-list__container">
    <PkmerNavigationRoot @change="handleStyleChange">
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
      <PlayList :play-list="playList" />
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
