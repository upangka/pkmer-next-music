import type { InjectionKey } from 'vue'
import type { TabsContext } from './types'

export const TabPanneName = 'PkmerTabPanne'

export const tabsKey: InjectionKey<TabsContext> = Symbol('tabs context')
