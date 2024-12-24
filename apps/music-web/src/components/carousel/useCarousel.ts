import { ref, watch, shallowRef, provide, onMounted, onBeforeUnmount, computed } from 'vue'
import type { CarouselItem, CarouselProps } from './types'
import { carouseContextKey, THROTTLE_TIME } from './constants'
import { debounce, isUndefined } from '@utils'

function useCarousel(props: CarouselProps) {
  // 当前轮播图片
  const activeIndex = ref(0)
  const children = shallowRef<CarouselItem[]>([])
  const root = ref<HTMLElement | null>(null)
  const timer = ref<number | null>(null)
  const hover = ref(false)
  const isCardType = computed(() => props.type === 'card')
  const arrowDisplay = computed(() => props.arrow !== 'never')
  console.log(props)
  provide(carouseContextKey, {
    root,
    children,
    addChild,
    isCardType,
    cardScale: props.cardScale!
  })

  onMounted(() => {
    // 确保所有的children已经挂在
    // 然后immediate进行初始化
    watch(
      () => activeIndex.value,
      (_, oldIndex) => {
        // 触发每个元素translate的入口
        doCarousel(oldIndex)
      },
      { immediate: true }
    )
    // 定时器启动的入口
    startTimer()
  })

  onBeforeUnmount(() => {
    pauseTimer()
  })

  /**
   * 开启自动宣汉
   */
  function startTimer() {
    if (props.autoplay) {
      timer.value = setInterval(() => {
        doUpdateActiveIndex(activeIndex.value + 1)
      }, 3000)
    }
  }

  function pauseTimer() {
    if (timer.value != undefined) {
      clearInterval(timer.value)
      timer.value = null
    }
  }

  function addChild(item: CarouselItem) {
    children.value = [...children.value, item]
  }

  /**
   * 改变响应式的activeIndex值，通过watch activeIndex来进行调用子元素的触发
   * @param updateIndex 要轮播的下一页
   */
  function doUpdateActiveIndex(updateIndex: number) {
    const length = children.value.length

    if (updateIndex < 0) {
      activeIndex.value = length - 1
    } else if (updateIndex > length - 1) {
      activeIndex.value = 0
    } else {
      activeIndex.value = updateIndex
    }
  }

  const handleClick = debounce((index: number) => {
    pauseTimer()
    doUpdateActiveIndex(index)
  }, THROTTLE_TIME)

  function doCarousel(prevIndex?: number) {
    children.value.forEach((item, index) => {
      item.translateItem(index, activeIndex.value, prevIndex)
    })
  }

  function handleMouseEnter() {
    pauseTimer()
    updateHoverStatus(true)
  }

  function handleMouseLeave() {
    startTimer()
    updateHoverStatus(false)
  }

  function updateHoverStatus(status: boolean) {
    hover.value = status
  }

  return {
    root,
    activeIndex,
    children,
    arrowDisplay,
    hover,
    addChild,
    updateActiveIndex: handleClick,
    handleMouseEnter,
    handleMouseLeave
  }
}

export default useCarousel
