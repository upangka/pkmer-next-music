import { createContextScope } from '@/utils/create-context'
import type { ImageLoadingStatus } from '@/hooks/useImageLoadingStatus'

type AvatarContext = {
  imageLoadingStatus: ImageLoadingStatus
  onImageLoadingStatusChange: (status: ImageLoadingStatus) => void
}

const createAvatarContext = createContextScope('Avatar')

const [AvatarProvider, useAvatarContext] = createAvatarContext<AvatarContext>()

export { AvatarProvider, useAvatarContext, type ImageLoadingStatus, type AvatarContext }
