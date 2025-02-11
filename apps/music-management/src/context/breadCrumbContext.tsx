import type { BreadCrumb } from '@pkmer-music/management/types'
import { createContext, useState, useContext, use } from 'react'
interface BreadCrumbProviderProps {
  children: React.ReactNode
}

interface BreadCrumbContextProps {
  breadcrumbs: BreadCrumb[]
  setBreadcrumbs: (breadcrumbs: BreadCrumb[]) => void
}

export const BreadCrumbContext = createContext<BreadCrumbContextProps | undefined>(undefined)

export const useBreadCrumbContext = () => {
  const context = use(BreadCrumbContext)
  if (!context) {
    throw new Error('useBreadCrumbContext must be used within a BreadCrumbProvider')
  }
  return context
}

export const BreadCrumbProvider: React.FC<BreadCrumbProviderProps> = ({ children }) => {
  const [breadcrumbs, setBreadcrumbs] = useState<BreadCrumb[]>([])

  return (
    <BreadCrumbContext.Provider
      value={{
        breadcrumbs,
        setBreadcrumbs
      }}
    >
      {children}
    </BreadCrumbContext.Provider>
  )
}
