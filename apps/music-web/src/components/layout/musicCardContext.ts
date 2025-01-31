import type { InjectionKey } from 'vue'
import type { MusicCard } from '@pkmer-music/web/types'
export interface MusicCardContext {
  onClick: (card: MusicCard) => void
}

export const musicCardKey: InjectionKey<MusicCardContext> = Symbol('Music Card Context')
