import type { ComponentInternalInstance, Ref } from 'vue'

interface AddChild {
  (node: ComponentInternalInstance): void
}

// 添加其他位置
export type TabPosition = 'left'

export interface TabsContext {
  currentPanne: Ref<string>
  tabPosition: TabPosition
  addChild: AddChild
  changeCurrentPanne: (value: string) => void
}
