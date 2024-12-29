<script lang="ts" setup>
</script>

<template>
  <ul class="navigation-list">
    <slot></slot>
  </ul>
</template>

<style lang="scss" scoped>
.navigation-list {
  /* @apply flex items-center justify-start gap-6; */
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  gap: 24px;
}
</style>
