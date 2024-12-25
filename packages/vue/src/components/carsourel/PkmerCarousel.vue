<script lang="ts">
import type { CarouselProps as Props } from './types'
</script>

<script lang="ts" setup>
import useCarousel from './useCarousel'
import { computed } from "vue"
const props = withDefaults(defineProps<Props>(), {
  type: '',
  cardScale: 0.83,
  autoplay: true,
  arrow: 'hover'
})

const {
  root,
  activeIndex,
  arrowDisplay,
  hover,
  updateActiveIndex,
  handleMouseEnter,
  handleMouseLeave } = useCarousel(props)


const showBtn = computed(() => hover.value || props.arrow === 'always')

function handleLeftClick() {
  updateActiveIndex(activeIndex.value - 1)
}

function handleRightClick() {
  updateActiveIndex(activeIndex.value + 1)
}
</script>

<template>
  <section ref="root" class="relative h-fit w-full overflow-hidden" @mouseenter="handleMouseEnter"
    @mouseleave="handleMouseLeave">
    <Transition v-if="arrowDisplay" name="btn-left">
      <button @click="handleLeftClick" v-show="showBtn" class="btn btn-left">
        <iconify-icon icon="simple-line-icons:arrow-left"></iconify-icon>
      </button>
    </Transition>

    <Transition v-if="arrowDisplay" name="btn-right">

      <button @click="handleRightClick" v-show="showBtn" class="btn btn-right">
        <iconify-icon icon="simple-line-icons:arrow-right"></iconify-icon>
      </button>
    </Transition>

    <div class="relative h-[300px] pkmer-carousel--container">
      <slot></slot>
    </div>
  </section>
</template>

<style lang="scss" scoped>
.pkmer-carousel--container {

  /* https://vuejs.org/api/sfc-css-features.html#slotted-selectors */
  :slotted(div) {

    &:nth-child(1) {
      @apply bg-green-600
    }

    &:nth-child(2) {
      @apply bg-yellow-600
    }

    &:nth-child(3) {
      @apply bg-blue-600
    }

    &:nth-child(4) {
      @apply bg-purple-600
    }

    &:nth-child(5) {
      @apply bg-red-600
    }
  }


}


.btn {
  @apply absolute top-1/2 -translate-y-1/2 z-10 text-white bg-slate-400 rounded-full p-2 w-8 h-8 flex justify-center items-center;

  &:hover {
    @apply bg-slate-500;
  }

  &.btn-left {
    @apply left-4;
  }

  &.btn-right {
    @apply right-4;
  }
}


.btn-left-enter-active,
.btn-left-leave-active,
.btn-right-enter-active,
.btn-right-leave-active {
  @apply duration-500 ease-in-out;
}

/* 不在DOM的效果 */
.btn-left-enter-from,
.btn-left-leave-to {
  @apply -translate-x-3 opacity-0
}

/* 不在DOM的效果 */
.btn-right-enter-from,
.btn-right-leave-to {
  @apply translate-x-3 opacity-0
}
</style>
