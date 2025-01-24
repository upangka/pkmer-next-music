<script setup lang="tsx">
import { getCurrentInstance, provide, createVNode } from 'vue'
import PkmerTabNav from './PkmerTabNav.vue'
import { TabNavRenderer } from './TabNavRenderer'
import { TabPanneName, tabsKey } from './constants'
import getAllSlotByComponentName from './flat'
const vm = getCurrentInstance()!

const { children, addChild } = getAllSlotByComponentName(vm, TabPanneName)

provide(tabsKey, {
  addChild
})

// 获取所有的pannel

function render() {
  return createVNode(PkmerTabNav, {
    pannes: children.value
  })
}

const header = (
  <div>
    <TabNavRenderer render={render} />
  </div>
)
</script>
<template>
  <div>
    <!-- pannes -->
    <div class="content">
      <slot></slot>
    </div>
    {{ header }}
  </div>
</template>
