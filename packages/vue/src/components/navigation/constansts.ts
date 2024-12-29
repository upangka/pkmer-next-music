import type { InjectionKey } from 'vue'
import type { NavContext } from './types'
export const initItemGap = 30
export const navigationKey: InjectionKey<NavContext> = Symbol('navigationKey')
