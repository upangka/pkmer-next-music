<script lang="ts">
interface AvatarImageProps {
  src: string
  alt?: string
}
</script>
<script setup lang="ts">
import { inject, watch } from 'vue'
import { avatarContextKey } from './constants'
import { useImageLoadingStatus } from './useImageLoadingStatus'
const props = defineProps<AvatarImageProps>()
const context = inject(avatarContextKey)!
const state = useImageLoadingStatus(props.src)

watch(
  () => state.value,
  newVal => {
    if (newVal !== 'idle') context.onImageLoadingStatusChange(newVal)
  },
  {
    immediate: true
  }
)
</script>
<template>
  <img v-if="context.imageLoadingStatus.value === 'loaded'" v-bind="props" />
</template>
