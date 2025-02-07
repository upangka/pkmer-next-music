'use client'
import { useState } from 'react'
import { LoginForm, SignUpForm, RectCheckbox } from '@pkmer-music/management/components/form'
import style from './page.module.scss'
export default function Page() {
  const [isChecked, setChecked] = useState(true)
  const isLogin = isChecked
  const isSignUp = !isChecked

  function handleOnChange(checked: boolean) {
    console.log(checked)
    setChecked(checked)
  }

  const liStyle = {
    transition: `color ${style.duration} ease-in-out`
  } satisfies React.CSSProperties

  return (
    <section className={style.pageContainer}>
      <h1 className={style.title}>Pkmer Next Music 后台管理系统</h1>

      <section className='shadow-xlg m-auto flex h-[500px] w-fit flex-col items-center justify-center gap-6 overflow-hidden rounded-lg bg-white bg-opacity-60 p-10'>
        <ul className='flex gap-[80px]'>
          <li
            style={{
              ...liStyle,
              color: isLogin ? style.titleColor : ''
            }}
            className='text-xl font-bold text-gray-300'
          >
            登录
          </li>

          <li
            style={{
              ...liStyle,
              color: isSignUp ? style.titleColor : ''
            }}
            className='text-xl font-bold text-gray-300'
          >
            注册
          </li>
        </ul>
        <RectCheckbox width={60} onChange={handleOnChange} />

        <div className={style.cardContainer}>
          <div
            style={{
              transform: isChecked ? 'rotateY(0deg)' : 'rotateY(180deg)'
            }}
            className={style.cardWrapper}
          >
            <div>
              <LoginForm />
            </div>
            <div>
              <SignUpForm />
            </div>
          </div>
        </div>
      </section>
    </section>
  )
}
