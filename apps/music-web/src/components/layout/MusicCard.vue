<script lang="ts" setup>
import type { Song } from '@pkmer-music/web/assets/types'
interface Props {
  card: Song
}

const { card } = defineProps<Props>()
</script>

<template>
  <div class="group h-fit w-[200px]">
    <div class="h-[200px] w-full overflow-hidden rounded-2xl hover:shadow-xl">
      <img :src="card.imgurl" alt="" class="object-cover" />
    </div>
    <p class="truncate p-2 text-gray-500 group-hover:text-black text-left">{{ card.dissname }}</p>
  </div>
</template>
