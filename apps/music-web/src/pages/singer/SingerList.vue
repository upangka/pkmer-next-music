<script lang="ts" setup>
import { PlayList } from "@pkmer-music/web/components"
import { NavigationIndicator, NavigationItem, NavigationList, NavigationRoot } from "@pkmer-music-ui/vue/navigation"
import { singerStyles } from "@pkmer-music/web/enums"
import songs from '@pkmer-music/web/assets/songs.json'
</script>

<template>
  <div class="singer-list__container">
    <NavigationRoot>
      <NavigationList :show-border="false" :item-gap="10">
        <template v-for="singerStyle in singerStyles" :key="singerStyle.type">
          <NavigationItem>
            {{ singerStyle.name }}
          </NavigationItem>
        </template>
      </NavigationList>
      <NavigationIndicator color="#ec4899" />
    </NavigationRoot>

    <!-- 歌手列表展示start -->
    <section class="mt-3">
      <PlayList path="/xxx" :play-list="songs.slice(0, 30)" />
    </section>
    <!-- 歌手列表展示end -->
  </div>
</template>

<style lang="scss" scoped>
@use "@pkmer-music/web/assets/styles/mixins.scss" as PkmerMixin;

.singer-list__container {
  @include PkmerMixin.play-list__container();
  height: 100%;
  margin-top: 10px;
}
</style>
