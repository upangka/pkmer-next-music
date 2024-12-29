import type { Ref } from 'vue'
export type NavItem = {
  target: HTMLElement
  width: number
  offsetX: number
}

export interface ActiveCurrentItem {
  (item: NavItem): void
}

export interface UpdateItemGap {
  (itemGap: number): void
}

export type NavContext = {
  itemGap: Ref<number> // 间距
  updateItemGap: UpdateItemGap // 更新间距
  currentActiveItem: Ref<NavItem | null> // 当前激活的item
  updateCurrentActiveItem: ActiveCurrentItem // 更新当前激活的item
}
