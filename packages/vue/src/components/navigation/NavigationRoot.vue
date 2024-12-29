<script lang="ts" setup>
import { provide, ref, watch } from 'vue'
import { navigationKey } from './constansts'
import type { NavItem } from './types'

/**
  * 当前激活的选项卡
  */
const currentItem = ref<NavItem | null>(null)

watch(currentItem, newNavItem => {
  console.log(newNavItem)
})

function updateCurrentActiveItem(activeCurrentItem: NavItem) {
  currentItem.value = activeCurrentItem
}

provide(navigationKey, {
  currentActiveItem: currentItem,
  updateCurrentActiveItem
})

</script>

<template>
  <nav class="navigation-root">
    <slot></slot>
  </nav>
</template>

<style lang="scss" scoped>
.navigation-root {
  position: relative;
}
</style>
