'use client'
import { Icon } from '@iconify/react'

interface Props {
  icon: string
  size?: number
}
const PkmerIcon: React.FC<Props> = ({ icon, size = 30 }) => {
  return <Icon icon={icon} width={size} height={size} />
}

export default PkmerIcon
