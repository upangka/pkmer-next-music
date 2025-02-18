'use client'
import { useId, useRef, useEffect, useLayoutEffect, useState } from 'react'
import { useFormContext } from './pkmer-form'
import { Label } from '@pkmer-music/management/components/ui/label'
import clsx from 'clsx'
interface PkmerFormItemProps {
  id?: string
  label: string
  children: React.ReactNode
  labelGap?: number // 标签与输入框的间距,间距，方便统一错误提示的距离
  errorPrompt?: React.ReactNode // 错误提示
}

export const PkmerFormItem: React.FC<PkmerFormItemProps> = ({
  id,
  label,
  children,
  labelGap = 8,
  errorPrompt
}) => {
  const { maxWidthLabel, registerLabel, isWidthCalculated } = useFormContext()
  const labelRef = useRef<HTMLLabelElement>(null)

  const itemId = useId()

  useLayoutEffect(() => {
    if (labelRef.current) {
      const el = labelRef.current
      const width = window.getComputedStyle(el).width
      console.log(`${el.textContent}的宽度offsetWidth = ${el.offsetWidth} | width = ${width}`)
      registerLabel(el.offsetWidth)
    }
  }, [])

  const style: React.CSSProperties = labelRef.current
    ? {
        marginLeft: `${Number.parseInt(maxWidthLabel, 10) - labelRef.current.offsetWidth}px`,
        visibility: isWidthCalculated ? 'visible' : 'hidden'
      }
    : {}

  return (
    <div>
      <div
        id={id}
        className={clsx('flex flex-row items-center justify-center')}
        style={{
          visibility: isWidthCalculated ? 'visible' : 'hidden',
          gap: `${labelGap}px`
        }}
      >
        <div className='flex' style={style}>
          <Label
            ref={labelRef}
            htmlFor={itemId}
            className='inline-flex w-auto flex-[0_0_auto] whitespace-nowrap text-right'
          >
            {label}
          </Label>
        </div>

        <div id={itemId} className='flex flex-1'>
          {children}
        </div>
      </div>
      {/* error start */}
      <div
        id={id}
        style={{
          marginLeft: `${Number.parseInt(maxWidthLabel, 10) + labelGap}px`
        }}
      >
        {errorPrompt}
      </div>
      {/* error end */}
    </div>
  )
}
