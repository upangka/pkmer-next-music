<script lang="ts">
import type { ComponentInternalInstance, ComponentPublicInstance } from 'vue'

interface Props {
  pannes: ComponentInternalInstance[]
}
</script>
<script setup lang="ts">
import { getCurrentInstance, computed, inject } from 'vue'
import PkmerNavBar from './PkmerNavBar.vue'
import { tabsKey } from './constants'

const props = defineProps<Props>()
const vm = getCurrentInstance()!
const context = inject(tabsKey)!

const getRef = (el: ComponentPublicInstance | Element | null, i: number) => {
  if (el) {
    vm.refs[`tab-${i}`] = el
  }
}

const els = computed(() => {
  return props.pannes.map(pane => {
    return pane.subTree.el as HTMLElement
  })
})

function handleClick(name: string | unknown) {
  context.changeCurrentPanne(name as string)
}
</script>

<template>
  <div class="relative">
    <!-- bar -->
    <PkmerNavBar :els="els" />
    <!-- 标签 -->
    <div
      v-for="pane in props.pannes"
      :class="[context.currentPanne === pane.props.name && 'active']"
      :key="pane.uid"
      :ref="el => getRef(el, pane.uid)"
      @click="handleClick(pane.props.name)"
    >
      {{ pane.props.label }}
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
