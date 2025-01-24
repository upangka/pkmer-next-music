<script lang="ts" setup>
import { PlayList } from '@pkmer-music/web/components'
import {
  PkmerNavigationIndicator,
  PkmerNavigationItem,
  PkmerNavigationList,
  PkmerNavigationRoot
} from '@pkmer-music-ui/vue/navigation'
import { songPlayList } from '@pkmer-music/web/enums'
import songs from '@pkmer-music/web/assets/songs.json'
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
      <PlayList path="/xxx" :play-list="songs.slice(0, 30)" />
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
