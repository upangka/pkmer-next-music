import type { ComponentInternalInstance } from 'vue'

interface AddChild {
  (node: ComponentInternalInstance): void
}

export interface TabsContext {
  addChild: AddChild
}
