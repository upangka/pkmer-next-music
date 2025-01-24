import type { InjectionKey } from 'vue'
import type { DropdownContext } from './types'

export const dropdownMenuKey: InjectionKey<DropdownContext> = Symbol('dropdown')
