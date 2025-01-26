<script lang="ts">
interface Props {
  els: HTMLElement[]
}
</script>
<script setup lang="ts">
import { inject, computed, type CSSProperties } from 'vue'
import { tabsKey } from './constants'

defineOptions({
  name: 'PkmerNavBar'
})

const context = inject(tabsKey)!
const props = defineProps<Props>()

//todo 优化 这里，此时props els是空的
const barStyle = computed<CSSProperties>(() => {
  // todo pannel加active
  const el = props.els.find(el => el.classList.contains('active')) as HTMLElement
  console.log(el)
  if (!el) return {}

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
})

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
