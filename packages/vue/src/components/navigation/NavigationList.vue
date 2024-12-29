<script lang="ts">
interface Props {
  itemGap?: number
}
</script>

<script lang="ts" setup>
import { nextTick, onMounted, ref, computed, type CSSProperties } from 'vue';
import type { NavItem } from "./types"
import { useNavContext } from "./useNavContext"
const context = useNavContext()
const slotContainerRef = ref<HTMLUListElement | null>(null)
// 随机给一个很大的容器宽度，防止初始渲染的时候宽度为0
const containerWidth = ref<number>(9999)


const { itemGap = 30 } = defineProps<Props>()

const containerStyle = computed<CSSProperties>(() => {
  return {
    width: `${containerWidth.value}px`
  }
})



/**
 * 确保所有子元素DOM渲染完成之后
 * 获取第一个子元素的距离以及宽度
 */
onMounted(() => {
  nextTick(() => {
    calcWidth()
  })
})

/**
 * 这里是用space-evenly布局，所以需要计算容器的宽度。才好计算offsetLeft
 * 其实可以采用flex-start来布局，然后间距用作用padding来实现，这样就不需要计算容器的宽度了。
 */
function calcWidth() {
  const length = slotContainerRef?.value?.children.length || 0
  if (slotContainerRef.value && length > 0) {
    const firstChild = slotContainerRef.value.children[0] as HTMLLIElement
    // 统计每个元素的宽度以便确定容器的总宽度
    const widths: number[] = []
    for (let i = 0; i < length; i++) {
      const children = slotContainerRef.value.children[i] as HTMLLIElement
      widths.push(children.offsetWidth)
    }

    // 计算容器宽度 gap + width + gap
    containerWidth.value = itemGap * (widths.length + 1) + widths.reduce((prev, curr) => prev + curr, 0)
    console.log(widths)
    console.log({
      offsetLeft: firstChild.offsetLeft,
      clientLeft: firstChild.clientLeft,
      clientWidth: firstChild.clientWidth
    })
    context.updateCurrentActiveItem({
      target: firstChild,
      width: firstChild.offsetWidth + itemGap,
      offsetX: itemGap / 2  // 这里不能用firstChildren.offsetLeft,因为我们现在width还没有计算出来。
    } satisfies NavItem)
  }
}
</script>

<template>
  <ul :style="containerStyle" class="navigation-list" ref="slotContainerRef">
    <slot></slot>
  </ul>
</template>

<style lang="scss" scoped>
.navigation-list {
  position: relative;
  padding: 10px 0px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
  border-bottom: 5px double black;
  border-top: 1px solid black;
}
</style>
