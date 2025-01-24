import type { Ref } from 'vue'
export interface DropdownContext {
  /**
   * @description: 是否显示下拉菜单
   */
  visible: Ref<boolean>
  /**
   * @description: 显示下拉菜单
   */
  show: () => void
  /**
   * @description: 隐藏下拉菜单
   */
  hide: () => void
}
