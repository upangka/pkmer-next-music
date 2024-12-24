'use client'
import { useState } from 'react'
import { AvatarProvider, type ImageLoadingStatus } from './use-avatar-context'
import { pkmer } from '@/utils/comp-factory'

export interface AvatarRootProps extends React.ComponentPropsWithRef<'div'> {}

export const AvatarRoot: React.FC<AvatarRootProps> = props => {
  const { ref, ...avatarProps } = props
  const [imageStatus, setImageStatus] = useState<ImageLoadingStatus>('idle')
  return (
    <AvatarProvider imageLoadingStatus={imageStatus} onImageLoadingStatusChange={setImageStatus}>
      <pkmer.div {...avatarProps} ref={props.ref} />
    </AvatarProvider>
  )
}
