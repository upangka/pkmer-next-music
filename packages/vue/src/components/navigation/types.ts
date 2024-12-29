import type { Ref } from 'vue'
export type NavItem = {
  target: HTMLElement
  width: number
  offsetX: number
}

export interface ActiveCurrentItem {
  (item: NavItem): void
}

export type NavContext = {
  currentActiveItem: Ref<NavItem | null>
  updateCurrentActiveItem: ActiveCurrentItem
}
