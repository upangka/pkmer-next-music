<script setup lang="ts">
import { inject } from 'vue'
import { dropdownMenuKey } from './constants'
const { visible } = inject(dropdownMenuKey)!
</script>
<template>
  <ul v-if="visible" class="relative w-fit rounded-md bg-black p-2 text-white">
    <div class="indicator"></div>
    <slot></slot>
  </ul>
</template>
<style lang="scss" scoped>
$dropdown-background: black;
.indicator {
  position: absolute;
  width: 0;
  height: 0;
  top: -9px;
  left: 50%;
  margin-left: -10px;
  border-style: solid;
  border-width: 0px 10px 10px 10px;
  border-color: transparent transparent $dropdown-background transparent;
}
</style>
