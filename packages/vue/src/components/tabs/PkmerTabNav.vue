<script lang="ts">
import type { ComponentInternalInstance, ComponentPublicInstance } from 'vue'

interface Props {
  pannes: ComponentInternalInstance[]
}
</script>
<script setup lang="ts">
import { computed, inject, ref } from 'vue'
import PkmerNavBar from './PkmerNavBar.vue'
import { tabsKey } from './constants'

const props = defineProps<Props>()
const context = inject(tabsKey)!
const els = ref<HTMLElement[]>([])

const position = computed(() => 'is-' + context.tabPosition)

const addEl = (el: HTMLElement) => {
  if (el) {
    els.value.push(el)
  }
}

function handleClick(name: string | unknown) {
  context.changeCurrentPanne(name as string)
}

function changeToStr(pane: ComponentInternalInstance) {
  return pane.props.name as string
}
</script>

<template>
  <div
    class="tab-nav__container relative flex h-fit flex-col items-center justify-center gap-3 px-3"
    :class="position"
  >
    <!-- bar -->
    <PkmerNavBar :els="els" />
    <!-- 标签 -->
    <div
      v-for="pane in props.pannes"
      :id="changeToStr(pane)"
      :class="[context.currentPanne.value === pane.props.name && 'active', 'tab']"
      :key="pane.uid"
      :ref="el => addEl(el as HTMLElement)"
      @click="handleClick(pane.props.name)"
    >
      {{ pane.props.label }}
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use './variables.scss' as tabs;
.tab-nav__container {
  &.is-left {
    border-right: 5px double rgb(125, 123, 123);
  }

  .active {
    color: tabs.$bar-color;
  }

  .tab {
    cursor: pointer;
  }
}
</style>
