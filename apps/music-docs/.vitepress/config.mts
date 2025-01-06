import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: 'Pkmer Next Music',
  description: '全栈开发文档',
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [{ text: '介绍', link: '/home' }],

    sidebar: [
      {
        text: '后端',
        link: '/server/index.md',
        items: [
          {
            text: 'MyBatis Dynamic SQL',
            link: '/server/mybatis/index.md',
            items: [{ text: 'select', link: '/server/mybatis/select.md' }]
          },
          {
            text: 'Docker',
            link: '/server/docker/index.md'
          }
        ]
      }
    ],

    socialLinks: [{ icon: 'github', link: 'https://github.com/vuejs/vitepress' }]
  }
})
