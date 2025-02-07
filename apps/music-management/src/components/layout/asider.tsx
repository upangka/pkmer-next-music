'use client'
import { useRouter, usePathname } from 'next/navigation'
import { PkmerIcon } from '@pkmer-music/management/components'
import asideStyle from './asider.module.scss'
import clsx from 'clsx'

interface Props {
  isShow?: boolean
}

const navInfos = [
  {
    name: '系统首页',
    icon: 'material-symbols-light:pie-chart-outline',
    router: '/dashboard'
  },
  {
    name: '用户管理',
    icon: 'uil:user',
    router: '/dashboard/user'
  },
  {
    name: '歌手管理',
    icon: 'ep:mic',
    router: '/dashboard/singer'
  },
  {
    name: '歌单管理',
    icon: 'mi:document',
    router: '/dashboard/songs'
  }
]

export const Asider: React.FC<Props> = ({ isShow = true }) => {
  const router = useRouter()
  const pathname = usePathname()
  // const itemClass = 'flex items-center justify-center gap-3 py-3'

  const uiStyle = {
    display: isShow ? 'block' : 'none'
  }

  return (
    <ul style={uiStyle} className={clsx('flex flex-col gap-32 border-r border-black px-10 py-5')}>
      {navInfos.map(item => (
        <li
          key={item.name}
          className={clsx(asideStyle.item, {
            [asideStyle.active]: pathname === item.router
          })}
          onClick={() => router.push(item.router)}
        >
          <PkmerIcon size={20} icon={item.icon} /> <span className='text-sm'>{item.name}</span>
        </li>
      ))}

      {/* <li className={asideStyle.item} onClick={() => router.push('/dashboard')}>
        <PkmerIcon size={20} icon='material-symbols-light:pie-chart-outline' />{' '}
        <span className='text-sm'>系统首页</span>
      </li>
      <li className={asideStyle.item}>
        <PkmerIcon size={20} icon='uil:user' /> <span className='text-sm'>用户管理</span>
      </li>
      <li className={asideStyle.item} onClick={() => router.push('/dashboard/singer')}>
        <PkmerIcon size={20} icon='ep:mic' /> <span className='text-sm'>歌手管理</span>
      </li>
      <li className={asideStyle.item}>
        <PkmerIcon size={20} icon='mi:document' /> <span className='text-sm'>歌单管理</span>
      </li> */}
    </ul>
  )
}
