'use client'
import { Icon } from '@iconify/react'
interface Props {
  icon: string
  size?: number
  className?: string
  onClick?: () => void
}

const noop = () => {}

const PkmerIcon: React.FC<Props> = props => {
  function handleClick() {
    // noop
    console.log('See Me')

    if (props.onClick) {
      props.onClick()
    }
  }

  // web components
  return (
    <Icon
      onClick={handleClick}
      className={props.className}
      icon={props.icon}
      width={props.size}
      height={props.size}
    />
  )
}

export default PkmerIcon
