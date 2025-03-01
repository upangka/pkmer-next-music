<script lang="ts">
interface Props {
  itemGap?: number // 标题间距
  showBorder?: boolean // 是否展示边框
  direction?: 'horizontal' | 'vertical' // 水平还是垂直
}
</script>

<script lang="ts" setup>
import { computed, nextTick, onMounted, ref } from 'vue'
import { initItemGap as itemGap } from './constansts'
import { useNavContext } from './useNavContext'
const context = useNavContext()
const slotContainerRef = ref<HTMLUListElement | null>(null)

const props = withDefaults(defineProps<Props>(), {
  itemGap: itemGap,
  showBorder: true,
  direction: 'horizontal'
})

onMounted(() => {
  initItemGap()
  nextTick(() => {
    const length = slotContainerRef.value?.children.length || 0
    if (slotContainerRef.value && length > 0) {
      const firstNavItem = slotContainerRef.value.children[0] as HTMLLIElement
      initIndicator(firstNavItem)
    }
  })
})

const isHorizontal = computed(() => props.direction === 'horizontal')

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

function initItemGap() {
  context.updateItemGap(props.itemGap)
}
</script>

<template>
  <ul
    :class="[
      'navigation-list',
      props.showBorder && (isHorizontal ? 'show-border' : 'show-border-vertical')
    ]"
    ref="slotContainerRef"
  >
    <slot></slot>
  </ul>
</template>

<style lang="scss" scoped>
.navigation-list {
  position: relative;
  padding: 10px 0px;
  width: fit-content;
  display: flex;
  align-items: center;
  justify-content: flex-start;

  &.show-border {
    flex-direction: row;
    border-bottom: 5px double black;
    border-top: 1px solid black;
  }

  &.show-border-vertical {
    flex-direction: column;
    border-right: 5px double black;
    border-left: 1px solid black;
  }
}
</style>
