<script setup lang="ts">
import { inject, computed } from 'vue'
import { avatarContextKey } from './constants'
const context = inject(avatarContextKey)!

const isShow = computed(() => context.imageLoadingStatus.value !== 'loaded')
</script>

<template>
  <span v-if="isShow">
    <slot></slot>
  </span>
</template>
