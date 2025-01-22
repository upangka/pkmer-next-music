<script setup lang="ts">
import { provide, ref } from 'vue'
import { avatarContextKey } from './constants'
import type { ImageLoadingStatus } from './types'

const imageLoadingStatus = ref<ImageLoadingStatus>('idle')

function onImageLoadingStatusChange(status: ImageLoadingStatus) {
  imageLoadingStatus.value = status
}

provide(avatarContextKey, {
  imageLoadingStatus,
  onImageLoadingStatusChange
})
</script>

<template>
  <slot></slot>
</template>
