<script lang="ts" setup>
import ContainerCompNames from './consts'
defineOptions({
  name: ContainerCompNames.header,
})
</script>

<template>
  <header>
    <slot></slot>
  </header>
</template>
