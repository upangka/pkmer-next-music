<script lang="ts" setup>
import ContainerCompNames from './consts'
defineOptions({
  name: ContainerCompNames.main,
})
</script>

<template>
  <header>
    <slot></slot>
  </header>
</template>
