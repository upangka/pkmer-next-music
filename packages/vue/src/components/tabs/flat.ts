import type { VNodeNormalizedChildren, VNode, ComponentInternalInstance } from 'vue'
import { isVNode, shallowRef } from 'vue'

/**
 * 获取所有的子节点
 * @param children
 * @returns
 */
function flat(children: VNodeNormalizedChildren | VNode) {
  const result: VNode[] = []
  const vnodes = Array.isArray(children) ? children : [children]
  vnodes.forEach(child => {
    if (Array.isArray(child)) {
      result.push(...flat(child))
    } else if (isVNode(child) && Array.isArray(child.children)) {
      result.push(child, ...flat(child.children))
    } else if (isVNode(child)) {
      result.push(child)
    }
  })
  return result
}

/**
 * 找到所有的子组件
 * @param vm
 * @param componentName
 * @returns
 */

export default function getAllSlotByComponentName(
  vm: ComponentInternalInstance,
  componentName: string
) {
  const children = shallowRef<ComponentInternalInstance[]>([])

  function addChild(vm: ComponentInternalInstance) {
    if (vm.type.name === componentName) {
      children.value.push(vm)
      children.value = [...children.value]
    }
  }

  return {
    children,
    addChild
  }
}
