<script lang="ts">
import type { VNode, Component } from 'vue'
</script>

<script lang="ts" setup>
import { useSlots, computed } from 'vue'
import ContainerCompNames from './consts'

const slots = useSlots()

/**
 * 如果容器中有header或者footer，就是垂直布局
 * 否则水平
 */
const isVertial = computed(() => {
  if (slots && slots.default) {
    const vnodes: VNode[] = slots.default()
    return vnodes.some(checkIsHeaderOrFooter)
  }

  function checkIsHeaderOrFooter(vnode: VNode) {
    const name = (vnode.type as Component).name!
    const { header, footer } = ContainerCompNames
    return name === header || name === footer
  }
  return false
})
</script>

<template>
  <section :class="['flex', isVertial ? 'flex-col' : 'flex-row']">
    <slot></slot>
  </section>
</template>
