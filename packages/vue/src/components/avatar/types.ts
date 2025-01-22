import type { Ref } from 'vue'
export type ImageLoadingStatus = 'loading' | 'loaded' | 'error' | 'idle'

export interface AvatarContext {
  imageLoadingStatus: Ref<ImageLoadingStatus>
  onImageLoadingStatusChange: (status: ImageLoadingStatus) => void
}
