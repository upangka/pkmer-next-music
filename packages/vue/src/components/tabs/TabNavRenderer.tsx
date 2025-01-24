import type { FunctionalComponent, VNode } from 'vue'

export const TabNavRenderer: FunctionalComponent<{ render: () => VNode }> = ({ render }) => {
  return render()
}
