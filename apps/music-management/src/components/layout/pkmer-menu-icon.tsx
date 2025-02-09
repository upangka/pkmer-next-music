'use client'
import clsx from 'clsx'
import styles from './pkmer-menu-icon.module.scss'
import { useState } from 'react'
interface MenuProps {
  changeStatus?: (status: boolean) => void
  className?: string
}

function noop() {
  return false
}
export const PkmerMenuIcon: React.FC<MenuProps> = ({ changeStatus = noop, className = '' }) => {
  const [isOpen, setOpen] = useState<boolean>(false)

  return (
    <div
      onClick={() => {
        setOpen(!isOpen)
        changeStatus(!isOpen)
      }}
      className={clsx(className, styles.menuWrapper, isOpen && styles.open)}
    >
      <div className={styles.menu}></div>
    </div>
  )
}
