<script lang="ts">
interface Props {
  label: string
  name: string
}
</script>
<script setup lang="ts">
import { TabPanneName } from './constants'
import { inject, onMounted, getCurrentInstance, computed } from 'vue'
import { tabsKey } from './constants'
defineOptions({
  name: TabPanneName
})

const props = defineProps<Props>()
const paneName = computed(() => props.name!)
const context = inject(tabsKey)!
const vm = getCurrentInstance()!

onMounted(() => {
  context.addChild(vm)
})
</script>
<template>
  <div :id="paneName">
    <slot></slot>
  </div>
</template>
<style lang="scss" scoped></style>
