<script lang="ts">
import type { MusicCard } from '@pkmer-music/web/types'
import type { Song } from '@pkmer-music/web/assets/types'

interface Props {
  card: MusicCard | Song
}
</script>

<script lang="ts" setup>
import { inject } from 'vue'
import { musicCardKey } from './musicCardContext'
const props = defineProps<Props>()

const context = inject(musicCardKey)!

function handleClick() {
  context.onClick(props.card)
}
</script>

<template>
  <div class="group h-fit w-[200px]" @click="handleClick">
    <div class="h-[200px] w-full overflow-hidden rounded-2xl hover:shadow-xl">
      <img :src="card.imgurl" alt="" class="object-cover" />
    </div>
    <p class="truncate p-2 text-left text-gray-500 group-hover:text-black">{{ card.dissname }}</p>
  </div>
</template>
