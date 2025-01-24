<script setup lang="ts">
import { inject } from 'vue'
import Indicator from './Indicator.vue'
import { dropdownMenuKey } from './constants'
const { visible } = inject(dropdownMenuKey)!
</script>
<template>
  <ul v-if="visible" class="relative w-fit rounded-md p-2 text-white">
    <Indicator />
    <slot></slot>
  </ul>
</template>
<style lang="scss" scoped>
@use './variables.scss' as dropdown;
ul {
  background-color: dropdown.$dropdown-background;
}
</style>
