import type { InjectionKey } from 'vue'
import type { AvatarContext } from './types'

export const avatarContextKey: InjectionKey<AvatarContext> = Symbol('avatar context key')
