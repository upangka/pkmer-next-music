'use client'
import { useId, useRef, useEffect } from 'react'
import { useFormContext } from './pkmer-form'
import { Label } from '@pkmer-music/management/components/ui/label'
interface PkmerFormItemProps {
  label: string
  children: React.ReactNode
}

export const PkmerFormItem: React.FC<PkmerFormItemProps> = ({ label, children }) => {
  // vue ref reactive
  const { maxWidthLabel, registerLabel } = useFormContext()
  // vue ref
  const labelRef = useRef<HTMLLabelElement>(null)

  const id = useId()

  //vue  onMounted watch
  useEffect(() => {
    if (labelRef.current) {
      const el = labelRef.current
      const width = window.getComputedStyle(el).width
      console.log(`${el.textContent}的宽度offsetWidth = ${el.offsetWidth} | width = ${width}`)
      registerLabel(el.offsetWidth)
    }
  }, [])

  const style: React.CSSProperties = labelRef.current
    ? {
        marginLeft: `${Number.parseInt(maxWidthLabel, 10) - labelRef.current.offsetWidth}px`
      }
    : {}

  return (
    <div className='flex flex-row gap-2'>
      <div style={style}>
        <Label
          ref={labelRef}
          htmlFor={id}
          className='inline-flex w-auto whitespace-nowrap border border-black text-right'
        >
          {label}
        </Label>
      </div>

      {/* label wrapper end */}
      <div id={id} className='border border-green-600'>
        {children}
      </div>
    </div>
  )
}
