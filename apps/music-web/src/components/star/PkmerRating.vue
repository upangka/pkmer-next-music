<script lang="tsx">
interface Props {
  rating: number
  maxRating?: number
  activeColor?: string
  inactiveColor?: string
  size?: number
}
</script>

<script lang="tsx" setup>
import { computed } from 'vue'
import { PkmerIcon } from '@pkmer-music-ui/vue/icon'
import type { JSX } from 'vue/jsx-runtime'

const props = withDefaults(defineProps<Props>(), {
  rating: 3.5,
  maxRating: 5,
  activeColor: '#ffa500',
  inactiveColor: '#a3a6ad',
  size: 30
})
const renderStars = computed<JSX.Element[]>(() => {
  return Array.from({ length: props.maxRating }, (_, i) => {
    if (props.rating >= i + 1) {
      return (
        <PkmerIcon
          icon='material-symbols:star'
          style={{ color: props.activeColor }}
          width={props.size}
          height={props.size}
        />
      )
    } else if (props.rating >= i + 0.5) {
      return (
        <PkmerIcon
          icon='material-symbols:star-half'
          style={{ color: props.activeColor }}
          width={props.size}
          height={props.size}
        />
      )
    } else {
      return (
        <PkmerIcon
          icon='material-symbols:star-outline'
          style={{ color: props.inactiveColor }}
          width={props.size}
          height={props.size}
        />
      )
    }
  })
})
</script>

<template>
  <div class="star-group">
    <component :is="star" v-for="(star, index) in renderStars" :key="index" />
  </div>
</template>
