<script lang="ts">
import type { ComponentInternalInstance, ComponentPublicInstance } from 'vue'

interface Props {
  pannes: ComponentInternalInstance[]
}
</script>
<script setup lang="ts">
import { getCurrentInstance } from 'vue'
const props = defineProps<Props>()
const vm = getCurrentInstance()!

const getRef = (el: ComponentPublicInstance | Element | null, i: number) => {
  if (el) {
    vm.refs[`tab-${i}`] = el
  }
}
</script>

<template>
  <div>
    <div v-for="pane in props.pannes" :key="pane.uid" :ref="el => getRef(el, pane.uid)"></div>
  </div>
</template>

<style lang="scss" scoped></style>
