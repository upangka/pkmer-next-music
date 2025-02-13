'use client'
import { createContext, useContext, useMemo, useRef, useState, useLayoutEffect } from 'react'
import clsx from 'clsx'
type PkmerFormContextType = {
  maxWidthLabel: string
  registerLabel: (width: number) => void
  isWidthCalculated: boolean
}

const PkmerFormContext = createContext<PkmerFormContextType | undefined>(undefined)

export function useFormContext() {
  const context = useContext(PkmerFormContext)
  if (!context) {
    throw new Error('useFormContext must be used within a PkmerForm')
  }
  return context
}

interface PkmerFormProps {
  className?: string
  children: React.ReactNode
}

/**
 * 表单的布局
 */
export const PkmerForm: React.FC<PkmerFormProps> = ({ children, className }) => {
  const [potentialLabelWidthArr, setPotentialLabelWidthArr] = useState<number[]>([])
  const [isWidthCalculated, setIsWidthCalculated] = useState(false)

  const maxWidthLabel = useMemo(() => {
    if (!potentialLabelWidthArr.length) return '0'
    const max = Math.max(...potentialLabelWidthArr)
    return max ? `${max}px` : ''
  }, [potentialLabelWidthArr])

  useLayoutEffect(() => {
    if (potentialLabelWidthArr.length) {
      console.log('form表单宽度计算完成')
      setIsWidthCalculated(true)
    }
  }, [potentialLabelWidthArr])

  function registerLabel(width: number) {
    setPotentialLabelWidthArr(prev => [...prev, width])
  }

  return (
    <PkmerFormContext.Provider
      value={{
        maxWidthLabel,
        registerLabel,
        isWidthCalculated
      }}
    >
      <form className={clsx(className, 'flex flex-col gap-4')}>{children}</form>
    </PkmerFormContext.Provider>
  )
}
