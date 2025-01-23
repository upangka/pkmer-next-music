<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { PlayList } from '@pkmer-music/web/components'
import { PkmerCarousel, PkmerCarouselItem } from '@pkmer-music-ui/vue/carsourel'
import songs from '@pkmer-music/web/assets/songs.json'
import { type Banner, getAllBanners } from '@pkmer-music/web/api/banner'

const banners = ref<Banner[]>([])

onMounted(async () => {
  banners.value.push(...(await getAllBanners()))
})
</script>

<template>
  <section class="flex h-4/5 justify-center">
    <!-- 幻灯片start -->
    <PkmerCarousel type="card" :autoplay="true" arrow="hover">
      <PkmerCarouselItem
        v-for="banner in banners"
        :key="banner.id"
        class="flex h-full justify-center text-2xl text-white"
      >
        <img :src="banner.url" class="h-full w-full object-cover" />
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
