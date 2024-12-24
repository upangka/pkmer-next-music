<script lang="ts" setup>
import ContainerCompNames from './consts'
defineOptions({
  name: ContainerCompNames.footer,
})
</script>

<template>
  <footer>
    <slot></slot>
  </footer>
</template>
