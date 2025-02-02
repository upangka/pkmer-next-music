<script lang="ts" setup>
import { onMounted } from 'vue'
import { PlayList } from '@pkmer-music/web/components'
import {
  PkmerNavigationIndicator,
  PkmerNavigationItem,
  PkmerNavigationList,
  PkmerNavigationRoot
} from '@pkmer-music-ui/vue/navigation'
import { singerStyles } from '@pkmer-music/web/enums'
import { getAllSong } from '@pkmer-music/web/api'

import songs from '@pkmer-music/web/assets/songs.json'

onMounted(async () => {
  const data = await getAllSong({
    sex: 'MALE'
  })
  console.log({ data })
})
</script>

<template>
  <div class="singer-list__container">
    <PkmerNavigationRoot>
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
      <PlayList path="/xxx" :play-list="songs.slice(0, 30)" />
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
