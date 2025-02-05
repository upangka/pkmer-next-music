'use client'
import { Icon } from '@iconify/react'

interface Props {
  icon: string
  size?: number
  className?: string
}
const PkmerIcon: React.FC<Props> = ({ icon, size = 30, className = '' }) => {
  return <Icon className={className} icon={icon} width={size} height={size} />
}

export default PkmerIcon
