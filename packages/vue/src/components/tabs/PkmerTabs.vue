<script lang="tsx">
interface Props {
  tabPosition?: 'left'
}
</script>

<script setup lang="tsx">
import { getCurrentInstance, provide, createVNode, ref } from 'vue'
import PkmerTabNav from './PkmerTabNav.vue'
import { TabPanneName, tabsKey } from './constants'
import getAllSlotByComponentName from './flat'
const vm = getCurrentInstance()!

const props = withDefaults(defineProps<Props>(), {
  tabPosition: 'left'
})

const { children, addChild } = getAllSlotByComponentName(vm, TabPanneName)

const currentPanne = ref('first')

provide(tabsKey, {
  currentPanne,
  tabPosition: props.tabPosition,
  addChild,
  changeCurrentPanne
})

function changeCurrentPanne(name: string) {
  console.log('看见我了吗')
  currentPanne.value = name
}
</script>
<template>
  <div>
    <!-- pannes -->
    <div class="content">
      <slot></slot>
    </div>
    <!-- headers -->
    <PkmerTabNav :pannes="children" />
  </div>
</template>
