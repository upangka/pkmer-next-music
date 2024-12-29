<script lang="ts">
interface Props {
  itemGap?: number
}
</script>

<script lang="ts" setup>
import { nextTick, onMounted, ref } from 'vue';
import type { NavItem } from "./types"
import { initItemGap } from "./constansts"
import { useNavContext } from "./useNavContext"
const context = useNavContext()
const slotContainerRef = ref<HTMLUListElement | null>(null)

const { itemGap = initItemGap } = defineProps<Props>()


onMounted(() => {
  nextTick(() => {
    const length = slotContainerRef.value?.children.length || 0
    if (slotContainerRef.value && length > 0) {
      const firstNavItem = slotContainerRef.value.children[0] as HTMLLIElement
      initIndicator(firstNavItem)
    }
  })
})
/**
 * 初始化指示器，因为指示器只有点击具体title之后才会显示
 * 这里需要提供初始值给它
 */
function initIndicator(firstNavItem: HTMLLIElement) {
  context.updateCurrentActiveItem({
    target: firstNavItem,
    offsetX: firstNavItem.offsetLeft,
    width: firstNavItem.offsetWidth
  })
}

</script>

<template>
  <ul class="navigation-list" ref="slotContainerRef">
    <slot></slot>
  </ul>
</template>

<style lang="scss" scoped>
.navigation-list {
  position: relative;
  padding: 10px 0px;
  width: fit-content;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  border-bottom: 5px double black;
  border-top: 1px solid black;
}
</style>
