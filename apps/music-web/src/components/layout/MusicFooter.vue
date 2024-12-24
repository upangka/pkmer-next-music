<script lang="ts" setup>
const footerInfo = ['关于 | 帮助 | 条款 | 反馈', 'Copyright © 2024 Pkmer | Happy Coding'] as const
</script>

<template>
  <section class="flex flex-col items-center justify-center gap-3 py-5 text-gray-500">
    <template v-for="(item, i) in footerInfo" :key="i">
      <p v-if="i === footerInfo.length - 1" class="flex items-center gap-2">
        <iconify-icon icon="devicon:vuejs"></iconify-icon>
        <span>{{ item }}</span>
        <iconify-icon icon="arcticons:happyapk-app"></iconify-icon>
      </p>
      <span v-else>{{ item }}</span>
    </template>
  </section>
</template>
