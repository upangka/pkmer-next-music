import React from 'react'
import { useAvatarContext } from './use-avatar-context'
import { useImageLoadingStatus } from '@pkmer-ui/react/hooks/useImageLoadingStatus'

interface AvatarImageProps extends React.ComponentPropsWithRef<'img'> {}

const AvatarImage: React.FC<AvatarImageProps> = props => {
  const { ref, src, alt, ...imgProps } = props
  const imgStatus = useImageLoadingStatus(src)
  const context = useAvatarContext()
  // todo ref callback
  React.useLayoutEffect(() => {
    if (imgStatus !== 'idle') {
      context.onImageLoadingStatusChange(imgStatus)
    }
  }, [imgStatus])

  return <>{imgStatus === 'loaded' ? <img ref={ref} src={src} alt={alt} {...imgProps} /> : null}</>
}

export { AvatarImage, type AvatarImageProps }
