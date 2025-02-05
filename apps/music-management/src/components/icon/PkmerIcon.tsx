import { Icon } from '@iconify/react'
interface Props {
  icon: string
  size?: number
  className?: string
  onClick?: () => void
}

const noop = () => {}

const PkmerIcon: React.FC<Props> = ({ icon, size = 30, className = '', onClick = noop }) => {
  // web components
  return <Icon onClick={onClick} className={className} icon={icon} width={size} height={size} />
}

export default PkmerIcon
