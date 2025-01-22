<script setup lang="ts">
import { inject, computed } from 'vue'
import { avatarContextKey } from './constants'
const context = inject(avatarContextKey)!

const isShow = computed(() => context.imageLoadingStatus.value !== 'loaded')
</script>

<template>
  <div v-if="isShow">
    <slot></slot>
  </div>
</template>
