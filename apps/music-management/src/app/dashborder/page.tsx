import PkmerIcon from '@pkmer-music/management/components/icon/PkmerIcon'
import { Avatar, AvatarFallback, AvatarImage } from '@pkmer-music/management/components/ui/avatar'
import Asider from './asider'
import Content from './content'
export default function DashBorder() {
  return (
    <>
      {/* header start */}
      <section className='flex items-center justify-between px-10 py-2 shadow-lg'>
        <div className='flex items-center gap-5'>
          <PkmerIcon icon='mynaui:menu-solid' />
          <p className='text-lg font-bold'>
            Pkmer-Next-Music <span className='text-base font-normal'>后台管理</span>
          </p>
        </div>
        <div className='flex items-center gap-5'>
          <Avatar>
            <AvatarImage src='https://github.com/shadcn.png' alt='@shadcn' />
            <AvatarFallback>CN</AvatarFallback>
          </Avatar>
          <p>admin</p>
        </div>
      </section>
      {/* header end */}

      <main className='flex gap-3'>
        <Asider />
        <Content />
      </main>
    </>
  )
}
