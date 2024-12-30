<script lang="ts">
interface Props {
  color?: string // 指示器颜色
}
</script>

<script lang="ts" setup>
import { computed, type CSSProperties } from "vue"
import { useNavContext } from "./useNavContext"

const { color = "black" } = defineProps<Props>()

const { currentActiveItem: activeItem } = useNavContext()

const indicatorStyle = computed<CSSProperties>(() => {
  if (!activeItem.value) return {}
  return {
    backgroundColor: color,
    width: `${activeItem.value.width}px`,
    transform: `translateX(${activeItem.value.offsetX}px)`,
  }
})
</script>

<template>
  <div :style="indicatorStyle" class="indicator "></div>
</template>

<style lang="scss" scoped>
.indicator {
  position: absolute;
  bottom: 0;
  height: 4px;
  transition: transform 0.3s linear, width 0.3s linear;
  user-select: none;
}
</style>
