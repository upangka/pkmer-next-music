<script lang="ts" setup>
import { PlayList } from '@pkmer-music/web/components'
import { PkmerCarousel, PkmerCarouselItem } from "@pkmer-music-ui/vue/carsourel"
import songs from '@pkmer-music/web/assets/songs.json'
</script>

<template>
  <section class="flex h-4/5 justify-center">
    <!-- 幻灯片start -->
    <PkmerCarousel type="card" :autoplay="true" arrow="hover">
      <PkmerCarouselItem v-for="i in 3" :key="i" class="flex h-full justify-center text-2xl text-white">
        {{ i }}
      </PkmerCarouselItem>
    </PkmerCarousel>
  </section>
  <!-- 幻灯片end -->

  <!-- 歌单start -->
  <PlayList title="歌 单 推 荐" path="/xxx" :play-list="songs.slice(0, 5)" />
  <!-- 歌单end -->
  <!-- 歌手start -->
  <PlayList title="歌 手 推 荐" path="/xxx" :play-list="songs.slice(5, 10)" />
  <!-- 歌手end -->
</template>
