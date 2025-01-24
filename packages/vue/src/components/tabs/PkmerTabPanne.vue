<script setup lang="ts">
import { TabPanneName } from './constants'
import { inject, onMounted, getCurrentInstance } from 'vue'
import { tabsKey } from './constants'
defineOptions({
  name: TabPanneName
})

const context = inject(tabsKey)!
const vm = getCurrentInstance()!

onMounted(() => {
  context.addChild(vm)
})
</script>
<template>
  <div></div>
</template>
<style lang="scss" scoped></style>
