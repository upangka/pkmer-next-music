<script lang="ts" setup>
import { computed, type CSSProperties } from "vue"
import type { NavItem } from "./types"
import { useNavContext } from "./useNavContext"

const context = useNavContext()

const itemStyle = computed<CSSProperties>(() => {
  return {
    paddingLeft: `${context.itemGap.value}px`,
    paddingRight: `${context.itemGap.value}px`,
  }
})

function calcSize(e: MouseEvent): NavItem {
  const target = e.target as HTMLElement
  return {
    target,
    offsetX: target.offsetLeft,
    width: target.offsetWidth
  }
}

function handleClick(e: MouseEvent) {
  const result = calcSize(e)
  context.updateCurrentActiveItem(result)
}
</script>

<template>
  <li :style="itemStyle" @click="handleClick" class="nav-item">
    <slot></slot>
  </li>
</template>

<style lang="scss" scoped>
.nav-item {
  user-select: none;
}
</style>
