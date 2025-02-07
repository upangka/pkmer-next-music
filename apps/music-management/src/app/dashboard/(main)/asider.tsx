'use client'
import { useRouter } from 'next/navigation'
import { PkmerIcon } from '@pkmer-music/management/components'
import clsx from 'clsx'
interface Props {
  isShow?: boolean
}

const Asider: React.FC<Props> = ({ isShow = true }) => {
  const router = useRouter()

  const itemClass = 'flex items-center justify-center gap-3 py-3'

  const uiStyle = {
    display: isShow ? 'block' : 'none'
  }

  return (
    <ul style={uiStyle} className={clsx('flex flex-col gap-32 border-r border-black px-10 py-5')}>
      <li className={itemClass} onClick={() => router.push('/dashboard')}>
        <PkmerIcon size={20} icon='material-symbols-light:pie-chart-outline' />{' '}
        <span className='text-sm'>系统首页</span>
      </li>
      <li className={itemClass}>
        <PkmerIcon size={20} icon='uil:user' /> <span className='text-sm'>用户管理</span>
      </li>
      <li className={itemClass} onClick={() => router.push('/dashboard/singer')}>
        <PkmerIcon size={20} icon='ep:mic' /> <span className='text-sm'>歌手管理</span>
      </li>
      <li className={itemClass}>
        <PkmerIcon size={20} icon='mi:document' /> <span className='text-sm'>歌单管理</span>
      </li>
    </ul>
  )
}

export default Asider
