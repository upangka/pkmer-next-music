<script setup lang="ts">
import { inject } from 'vue'
import { dropdownMenuKey } from './constants'
const { visible, hide, show } = inject(dropdownMenuKey)!
</script>

<template>
  <summary @click="visible ? hide() : show()" class="cursor-pointer list-none">
    <slot />
  </summary>
</template>
