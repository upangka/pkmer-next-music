import { inject, ref, getCurrentInstance, onMounted, unref } from 'vue'
import { carouseContextKey } from './constants'
import type { TranslateItem } from './types'
import { isUndefined } from '@pkmer/libs'

function useCarouselItem() {
  const instance = getCurrentInstance()!
  const context = inject(carouseContextKey)!
  const translate = ref(0)
  const animating = ref(false)
  const active = ref(false)
  // 在type为card的时候判断是否为C位，或者左右护法。换句话说就是是否展示
  const showInContainer = ref(false)

  // computed
  const { isCardType } = context

  onMounted(() => {
    context.addChild({
      uid: instance.uid,
      translateItem
    })
  })

  const translateItem: TranslateItem = (index, activeIndex, oldIndex) => {
    if (!isUndefined(oldIndex)) {
      if (typeof oldIndex === 'number') {
        handleAnimating(index, activeIndex, oldIndex)
      }
    }
    active.value = index === activeIndex
    index = processIndex(index, activeIndex)

    const _cardType = unref(isCardType)
    if (_cardType) {
      // 判断元素是不是C位以及左右护法
      showInContainer.value = Math.abs(index - activeIndex) <= 1
      translate.value = calcCardTranslateDistance(index, activeIndex)
    } else {
      translate.value = calcTranslateDistance(index, activeIndex)
    }
  }

  /**
   *
   * @param index 元素的索引
   * @param activeIndex 当前轮播的元素索引
   * @param oldIndex 之前轮播的元素索引
   */
  function handleAnimating(index: number, activeIndex: number, oldIndex: number) {
    // 当表当前元素是否为正在轮播的元素
    const isCurrentActive = index === activeIndex
    // 刚刚还在轮播展示的元素
    const prevActive = index === oldIndex

    animating.value = isCurrentActive || prevActive
    console.log('当前元素', index, '是否移动', animating.value)
  }

  /**
   * 计算一张轮播图占据全部页面宽度的移动
   * @param index
   * @param activeIndex
   * @returns
   */
  function calcTranslateDistance(index: number, activeIndex: number) {
    const rootEl = context.root.value
    if (rootEl) {
      const width = rootEl.offsetWidth
      return width * (index - activeIndex)
    }
    return 0
  }

  function calcCardTranslateDistance(index: number, activeIndex: number) {
    const { root, cardScale } = context
    const width = root.value!.offsetWidth

    if (activeIndex === index) {
      // 计算当前激活的轮播
      return width / 4
    } else if (index < activeIndex) {
      // 计算左边的移动，要考虑到缩放比例,为了修复(cardScale - 1)改成1.1
      return ((width / 2) * (cardScale - 1.1)) / 2
    } else if (index > activeIndex) {
      // 计算右边的移动，要考虑到缩放比例
      return (width / 4) * (2 + cardScale)
    }
    return 0
  }

  function processIndex(index: number, activeIndex: number) {
    const length = context.children.value.length

    if (activeIndex === 0 && index === length - 1) {
      return -1
    } else if (activeIndex === length - 1 && index === 0) {
      return length
    }

    return index
  }

  return {
    translate,
    animating,
    active,
    isCardType,
    cardScale: context.cardScale,
    showInContainer
  }
}

export default useCarouselItem
