import { useAvatarContext } from './use-avatar-context'
import { pkmer } from '@pkmer-ui/react/utils/comp-factory'
interface AvatarFallbackProps extends React.ComponentPropsWithRef<'span'> {}

const AvatarFallback: React.FC<AvatarFallbackProps> = props => {
  const { imageLoadingStatus } = useAvatarContext()
  const { ref, ...rest } = props
  if (imageLoadingStatus === 'loaded') {
    return null
  }
  return <pkmer.span ref={ref} {...rest} />
}

export { AvatarFallback, type AvatarFallbackProps }
