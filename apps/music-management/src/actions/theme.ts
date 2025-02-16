'use server'

export async function getTheme(): Promise<'dark' | 'light'> {
  console.log('获取配置的主题数据')
  return 'dark'
}
