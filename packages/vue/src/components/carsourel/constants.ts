import type { InjectionKey } from 'vue'
import type { CarouseContext } from './types'

const CAROUSEITEM_NAME = 'PkmerCarouselItem'
const carouseContextKey: InjectionKey<CarouseContext> = Symbol('carousel context key')
const THROTTLE_TIME = 300
export { CAROUSEITEM_NAME, carouseContextKey, THROTTLE_TIME }
