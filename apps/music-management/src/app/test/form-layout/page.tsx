'use client'
import { PkmerForm, PkmerFormItem } from '@pkmer-music/management/components'
import { Input } from '@pkmer-music/management/components/ui/input'
export default function Page() {
  return (
    <>
      <PkmerForm className='w-3/4'>
        <PkmerFormItem label='用户名'>
          <Input type='text' placeholder='请输入用户名' />
        </PkmerFormItem>
        <PkmerFormItem label='密码密码密码密码密码密码'>
          <Input type='password' placeholder='请输入密码' />
        </PkmerFormItem>
        <PkmerFormItem label='我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串'>
          <Input type='password' placeholder='请输入密码' />
        </PkmerFormItem>
        <PkmerFormItem label='符串我是很长的字符串我是很长的字符串我是很长的字符串'>
          <Input type='password' placeholder='请输入密码' />
        </PkmerFormItem>
      </PkmerForm>
    </>
  )
}
