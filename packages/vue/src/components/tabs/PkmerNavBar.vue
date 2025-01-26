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

const isLeft = computed(() => context.tabPosition === 'left')

// 这里需要监视长度变化，确保完成状态
watch(
  () => [context.currentPanne.value, props.els.length] as const,
  ([newValue, _]) => {
    console.log('此时收集的el个数', props.els.length)
    const el = props.els.find(el => el.id === newValue)
    if (el) {
      barStyle.value = updateStyle(el)
      console.log('bar 更新样式 success', barStyle.value)
    } else {
      console.warn('没有找到对应的元素')
    }
  },
  {
    immediate: true
  }
)

const updateStyle = (el: HTMLElement): CSSProperties => {
  console.log('el的offsetParent：', el.offsetParent)
  const position = ['left', 'right'] as const
  // 开始计算他的属性
  const positionName = position.includes(context.tabPosition) ? 'height' : 'width'

  const axis = positionName === 'height' ? 'y' : 'x'
  const pos = axis === 'y' ? 'top' : 'left'

  const offsetKey = `offset${capitalize(pos)}` as keyof HTMLElement
  const clientKey = `client${capitalize(positionName)}` as keyof HTMLElement
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
  <div :style="barStyle" class="bar-indicator__container" :class="[isLeft && 'is-left']"></div>
</template>
<style lang="scss" scoped>
@use './variables.scss' as tabs;
.bar-indicator__container {
  background-color: tabs.$bar-color;
  &.is-left {
    position: absolute;
    top: 0;
    right: -4px;
    width: tabs.$bar-size;
    transition: 0.3s transform ease;
  }
}
</style>
