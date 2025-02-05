import { PkmerIcon } from '@pkmer-music/management/components'
import contentStyle from './content.module.scss'
export default function Content() {
  // className={contentStyle.item}
  return (
    <section className='flex-1 border border-black py-5'>
      <ul className='flex items-center justify-evenly'>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-blue-600' size={50} icon='uil:user' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-green-600' size={50} icon='iconoir:headset' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-red-500' size={50} icon='ep:mic' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon className='text-yellow-600' size={50} icon='mi:document' />{' '}
          <div className={contentStyle.itemDescWrapper}>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
      </ul>

      {/* Charts start */}

      {/* Charts end */}
    </section>
  )
}
