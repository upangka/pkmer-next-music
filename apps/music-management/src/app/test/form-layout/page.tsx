import { PkmerForm, PkmerFormItem } from '@pkmer-music/management/components'

export default function Page() {
  return (
    <>
      <PkmerForm>
        <PkmerFormItem label='用户名'>
          <input type='text' placeholder='请输入用户名' />
        </PkmerFormItem>
        <PkmerFormItem label='密码密码密码密码密码密码'>
          <input type='password' placeholder='请输入密码' />
        </PkmerFormItem>
        <PkmerFormItem label='我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串'>
          <input type='password' placeholder='请输入密码' />
        </PkmerFormItem>
        <PkmerFormItem label='是很长的字符串我是很长的字符串我是很长的字符我是很长的字符串我是很长的字符串我是很长的字符我是很长的字符串我是很长的字符串我是很长的字符我是很长的字符串我是很长的字符串我是很长的字符我是很长的字符串我是很长的字符串我是很长的字符我是很长的字符串我是很长的字符串我是很长的字符我是很长的字符串我是很长的字符串我是很长的字符我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串我是很长的字符串'>
          <input type='password' placeholder='请输入密码' />
        </PkmerFormItem>
      </PkmerForm>
    </>
  )
}
