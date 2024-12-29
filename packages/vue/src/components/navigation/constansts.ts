import type { InjectionKey } from 'vue'
import type { NavContext } from './types'

export const navigationKey: InjectionKey<NavContext> = Symbol('navigationKey')
