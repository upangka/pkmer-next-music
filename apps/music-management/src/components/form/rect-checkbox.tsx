'use client'
import { ArrowUpLeftIcon } from '@heroicons/react/24/outline'
import clsx from 'clsx'
import rectCheckbox from './rect-checkbox.module.scss'
import { useState } from 'react'
import { noop } from '@pkmer-music/management/utils/helper'

interface Props {
  width?: number // default 60
  onChange?: (checked: boolean) => void // 暴露状态变化
}

const RectCheckbox: React.FC<Props> = ({ width = 60, onChange = noop }) => {
  const [checked, setChecked] = useState(true)

  const ballStyle = checked
    ? ({
        transform: 'translate(-10px, -50%) rotate(0deg)'
      } satisfies React.CSSProperties)
    : ({
        transform: `translate(calc(${width}px - 20px),-50%) rotate(-270deg)`
      } satisfies React.CSSProperties)

  function handleChecked(e: React.ChangeEvent<HTMLInputElement>) {
    const checked = e.target.checked
    setChecked(checked), onChange(checked)
  }

  return (
    <section className='my-1 py-2'>
      <input type='checkbox' id='rect-checkbox' onChange={handleChecked} className='hidden' />
      <label
        htmlFor='rect-checkbox'
        style={{
          width: `${width}px`
        }}
        className={rectCheckbox.label}
      >
        <div style={ballStyle}>
          <ArrowUpLeftIcon
            style={{
              color: rectCheckbox.mainColor,
              strokeWidth: rectCheckbox.strokeWidth
            }}
            className={clsx('h-5 w-5 text-4xl font-bold')}
          />
        </div>
      </label>
    </section>
  )
}

export default RectCheckbox
