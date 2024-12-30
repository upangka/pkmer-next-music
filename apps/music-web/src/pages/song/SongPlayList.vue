<script lang="ts" setup>
import { PlayList } from "@pkmer-music/web/components"
import { NavigationIndicator, NavigationItem, NavigationList, NavigationRoot } from "@pkmer-music-ui/vue/navigation"
import { songPlayList } from "@pkmer-music/web/enums"
import songs from '@pkmer-music/web/assets/songs.json'
</script>

<template>
  <div class="song-play-list__container">
    <NavigationRoot>
      <NavigationList :show-border="false" :item-gap="10">
        <template v-for="songPlay in songPlayList" :key="songPlay.type">
          <NavigationItem>
            {{ songPlay.name }}
          </NavigationItem>
        </template>
      </NavigationList>
      <NavigationIndicator color="#eab308" />
    </NavigationRoot>
    <!-- 歌单列表展示start -->
    <section class="mt-3">
      <PlayList path="/xxx" :play-list="songs.slice(0, 30)" />
    </section>
    <!-- 歌单列表展示end -->
  </div>
</template>

<style lang="scss" scoped>
@use "@pkmer-music/web/assets/styles/mixins" as PkmerMixin;


.song-play-list__container {
  @include PkmerMixin.play-list__container();
}
</style>
