<script lang="ts" setup>
import { provide, ref, watch } from 'vue'
import { navigationKey, initItemGap } from './constansts'
import type { NavItem } from './types'

const emit = defineEmits<{
  change: [target: HTMLElement]
}>()
/**
 * 当前激活的选项卡
 */
const currentItem = ref<NavItem | null>(null)
const itemGap = ref(initItemGap)

watch(currentItem, newNavItem => {
  console.log(newNavItem)
})

function updateCurrentActiveItem(activeCurrentItem: NavItem) {
  currentItem.value = activeCurrentItem
  emit('change', activeCurrentItem.target)
}

function updateItemGap(newItemGap: number) {
  itemGap.value = newItemGap
}

provide(navigationKey, {
  itemGap,
  updateItemGap,
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
