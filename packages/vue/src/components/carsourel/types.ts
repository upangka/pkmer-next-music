import type { Ref, ComputedRef } from 'vue'

type TranslateItem = {
  (index: number, activeIndex: number, oldIndex?: number): void
}

type AddItem = {
  (item: CarouselItem): void
}

type CarouselItem = {
  uid: number
  translateItem: TranslateItem
}

type CarouseContext = {
  root: Ref<HTMLElement | null>
  children: Ref<CarouselItem[]>
  addChild: AddItem
  isCardType: ComputedRef<boolean>
  cardScale: number
}

type ArrowType = 'always' | 'never' | 'hover'

interface CarouselProps {
  type?: '' | 'card'
  cardScale?: number
  autoplay?: boolean
  arrow?: ArrowType
}

export type { TranslateItem, CarouselItem, CarouseContext, CarouselProps }
