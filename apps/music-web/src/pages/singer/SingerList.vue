<script lang="ts">
import type { PageQuerySingerRes, MusicCard, SexType } from '@pkmer-music/web/types'
</script>

<script lang="ts" setup>
import { onMounted, ref, provide } from 'vue'
import { useRouter } from 'vue-router'
import { PlayList } from '@pkmer-music/web/components'
import { musicCardKey } from '@pkmer-music/web/components'

import {
  PkmerNavigationIndicator,
  PkmerNavigationItem,
  PkmerNavigationList,
  PkmerNavigationRoot
} from '@pkmer-music-ui/vue/navigation'
import { singerStyles } from '@pkmer-music/web/enums'
import { getAllSong } from '@pkmer-music/web/api'

// import songs from '@pkmer-music/web/assets/songs.json'
const router = useRouter()
const singersPage = ref<PageQuerySingerRes>()
const singers = ref<MusicCard[]>([])

provide(musicCardKey, {
  onClick: handleClick
})
onMounted(fetchSingerData)

async function fetchSingerData(name?: string | undefined, sex?: SexType) {
  const data = await getAllSong({
    name,
    sex
  })
  singersPage.value = data

  if (data && data.list) {
    singers.value = []
    data.list.forEach(item => {
      singers.value.push({
        id: item.id,
        imgurl: item.pic,
        dissname: item.name
      })
    })
  }
}

function handleChange(target: HTMLElement) {
  const name = target.textContent
  const style = singerStyles.filter(item => item.name === name)
  if (style) {
    const sex = style[0].type as unknown as SexType
    fetchSingerData(undefined, sex)
  }
}

function handleClick(card: MusicCard) {
  router.push(`singer-detail/${card.id}`)
}
</script>

<template>
  <div class="singer-list__container">
    <PkmerNavigationRoot @change="handleChange">
      <PkmerNavigationList :show-border="false" :item-gap="10">
        <template v-for="singerStyle in singerStyles" :key="singerStyle.type">
          <PkmerNavigationItem>
            {{ singerStyle.name }}
          </PkmerNavigationItem>
        </template>
      </PkmerNavigationList>
      <PkmerNavigationIndicator color="#ec4899" />
    </PkmerNavigationRoot>

    <!-- 歌手列表展示start -->
    <section class="mt-3">
      <PlayList path="/xxx" :play-list="singers" />
    </section>
    <!-- 歌手列表展示end -->
  </div>
</template>

<style lang="scss" scoped>
@use '@pkmer-music/web/assets/styles/mixins.scss' as PkmerMixin;

.singer-list__container {
  @include PkmerMixin.play-list__container();
  height: 100%;
  margin-top: 10px;
}
</style>
