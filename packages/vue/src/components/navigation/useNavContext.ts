import { navigationKey } from './constansts'
import { inject } from 'vue'

export function useNavContext() {
  const context = inject(navigationKey)!
  return context
}
