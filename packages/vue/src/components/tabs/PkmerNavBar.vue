<script lang="ts">
interface Props {
  els: HTMLElement[]
}
</script>
<script setup lang="ts">
import { inject, computed, watch, type CSSProperties, ref } from 'vue'
import { tabsKey } from './constants'

defineOptions({
  name: 'PkmerNavBar'
})

const context = inject(tabsKey)!
const props = defineProps<Props>()
const barStyle = ref<CSSProperties>()

watch(
  () => context.currentPanne.value,
  newValue => {
    const el = props.els.find(el => el.id === newValue)
    if (el) {
      barStyle.value = updateStyle(el)
      console.log('bar 更新样式 success')
    } else {
      console.warn('没有找到对应的元素')
    }
  }
)

const updateStyle = (el: HTMLElement): CSSProperties => {
  const position = ['left', 'right'] as const
  // 开始计算他的属性
  const positionName = position.includes(context.tabPosition) ? 'height' : 'width'

  const axis = positionName === 'height' ? 'y' : 'x'
  const pos = axis === 'y' ? 'top' : 'left'

  const offsetKey = `offset${capitalize(pos)}` as keyof HTMLElement
  const clientKey = `client${capitalize(axis)}` as keyof HTMLElement
  // 开始计算他的位置
  const offset = el[offsetKey] as number
  const size = el[clientKey] as number

  return {
    [positionName]: `${size}px`,
    transform: `translate${capitalize(axis)}(${offset}px)`
  } satisfies CSSProperties
}

function capitalize(str: string): string {
  if (!str) return ''
  return str.charAt(0).toUpperCase() + str.slice(1)
}
</script>
<template>
  <!-- 指示器 -->
  <div :style="barStyle"></div>
</template>
<style lang="scss" scoped></style>
