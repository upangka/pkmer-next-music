<script lang="ts" setup>
import { PlayList } from '@/components'
import { PkmerCarousel, PkmerCarouselItem } from "@pkmer-music-ui/vue/carsourel"
import songs from '@/assets/songs.json'
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
  <PlayList title="歌 单 推 荐" path="/xxx" :play-list="songs" />
  <!-- 歌单end -->
</template>
