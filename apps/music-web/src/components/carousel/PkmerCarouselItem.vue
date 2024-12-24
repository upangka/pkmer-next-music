<script lang="ts" setup>
import { computed, unref } from 'vue'
import type { CSSProperties } from 'vue'
import { CAROUSEITEM_NAME } from './constants'
import useCarouselItem from './useCarouselItem'

defineOptions({
  name: CAROUSEITEM_NAME,
})

const {
  translate,
  animating,
  active,
  isCardType,
  cardScale,
  showInContainer } = useCarouselItem()

const translateStyle = computed<CSSProperties>(() => {
  const x = unref(translate)
  return {
    transform: `translateX(${x}px)`,
  }
})


const activeStyle = computed<CSSProperties>(() => {
  const isActive = unref(active)
  const isShowInContainer = unref(showInContainer)
  const zIndex = (isActive ? 8 : 0) + (isShowInContainer ? 1 : 0);
  let scale = 1
  if (isCardType.value) {
    scale = isActive ? 1 : cardScale
  }
  return {
    zIndex,
    scale,
  }
})

/**
 * 注意card的层级问题，需要在animating的前面
 */
const carouselItemClass = computed(() => {
  return [
    animating.value ? ".carousel-item--animating" : "",
    {
      'carousel-item--card': isCardType.value,
    },
  ]
})

const animatingStyle = computed<CSSProperties>(() => {
  const time = unref(animating) ? 1 : 0.01
  if (isCardType.value) {
    return {}
  }
  return {
    transition: `transform ${time}s ease-in-out`,
  }
})


</script>

<template>
  <div class="absolute w-full" :style="[translateStyle, activeStyle, animatingStyle]" :class="carouselItemClass">
    <slot></slot>
  </div>
</template>

<style lang="css">
.carousel-item--animating {
  @apply transition-transform duration-[1000] ease-in-out;
}

.carousel-item--card {
  @apply w-1/2 transition-transform duration-[1000] ease-in-out;
}
</style>
