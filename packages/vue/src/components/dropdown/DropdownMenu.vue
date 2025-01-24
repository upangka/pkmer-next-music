<script setup lang="ts">
import { provide, ref } from 'vue'
import { dropdownMenuKey } from './constants'

const visible = ref(false)
provide(dropdownMenuKey, {
  visible,
  show,
  hide
})

function show() {
  visible.value = true
}

function hide() {
  visible.value = false
}
</script>

<template>
  <details class="relative flex flex-col items-center justify-center gap-3">
    <slot></slot>
  </details>
</template>
