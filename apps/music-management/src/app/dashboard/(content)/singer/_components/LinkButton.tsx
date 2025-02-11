'use client'
import { useRouter } from 'next/navigation'
import { Button } from '@pkmer-music/management/components/ui/button'

interface LinkButtonProps {
  href: string // 要跳转的页面
  children: React.ReactNode // 按钮内容
}

const LinkBtn: React.FC<LinkButtonProps> = ({ href, children }) => {
  const router = useRouter()

  function handleClick() {
    router.push(href)
  }

  return (
    <Button
      onClick={handleClick}
      className='rounded-md bg-gray-100 px-4 py-1 text-black hover:bg-blue-600 hover:text-white'
    >
      {children}
    </Button>
  )
}

export default LinkBtn
