import { PkmerIcon } from '@pkmer-music/management/components'
import contentStyle from './content.module.scss'
export default function Content() {
  // className={contentStyle.item}
  return (
    <section className='flex-1 border border-black py-5'>
      <ul className='flex items-center justify-evenly'>
        <li className={contentStyle.item}>
          <PkmerIcon size={50} icon='uil:user' />{' '}
          <div className='flex flex-col items-center justify-center gap-5'>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon size={50} icon='iconoir:headset' />{' '}
          <div className='flex flex-col items-center justify-center gap-5'>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon size={50} icon='ep:mic' />{' '}
          <div className='flex flex-col items-center justify-center gap-5'>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
        <li className={contentStyle.item}>
          <PkmerIcon size={50} icon='mi:document' />{' '}
          <div className='flex flex-col items-center justify-center gap-5'>
            <span className='text-xl font-bold'>233</span>
            <span>用户总数</span>
          </div>
        </li>
      </ul>
    </section>
  )
}
