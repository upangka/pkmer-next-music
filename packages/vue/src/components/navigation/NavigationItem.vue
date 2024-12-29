<script lang="ts" setup>
import type { NavItem } from "./types"
import { useNavContext } from "./useNavContext"

const context = useNavContext()

function calcSize(e: MouseEvent): NavItem {
  const target = e.target as HTMLElement
  console.log({
    offsetLeft: target.offsetLeft,
    clientLeft: target.clientLeft,
    clientWidth: target.clientWidth
  })
  return {
    target,
    offsetX: target.offsetLeft - 15,
    width: target.offsetWidth + 30
  }
}

function handleClick(e: MouseEvent) {
  const result = calcSize(e)
  context.updateCurrentActiveItem(result)
}
</script>

<template>
  <li @click="handleClick" class="nav-item">
    <slot></slot>
  </li>
</template>

<style lang="scss" scoped>
.nav-item {
  user-select: none;
}
</style>
