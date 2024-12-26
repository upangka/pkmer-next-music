<script lang="ts">
import type { Song } from '@pkmer-music/web/assets/types'
interface Props {
  title?: string
  path: string
  playList: Song[]
}
</script>

<script lang="ts" setup>
import { MusicCard } from '@pkmer-music/web/components'

const props = withDefaults(defineProps<Props>(), {
  playList: () => []
})
</script>

<template>
  <section>
    <h1 v-if="props.title" class="flex justify-center p-5 text-2xl font-bold">{{ props.title }}</h1>
    <section class="m-auto grid w-fit grid-cols-5 gap-3">
      <MusicCard v-for="(card, i) in props.playList" :key="i" :card />
    </section>
  </section>
</template>
