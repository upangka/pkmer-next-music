'use client'
/**
 * 面包屑服务端组件渲染时的辅助
 * 因为服务端组件无法使用useBreadcrumb
 * 所以单独封装一个客户端组件来处理
 */

import { useBreadCrumbContext } from './breadCrumbContext'
import { useEffect } from 'react'
import type { BreadCrumb } from '@pkmer-music/management/types'
interface BreadcrumbClientHelpProps {
  breadcrumbs: BreadCrumb[]
}

export const BreadcrumbClientHelp: React.FC<BreadcrumbClientHelpProps> = ({ breadcrumbs = [] }) => {
  const context = useBreadCrumbContext()
  useEffect(() => {
    context.setBreadcrumbs(breadcrumbs)
    return () => {
      context.setBreadcrumbs([])
    }
  }, [])
  return null
}
