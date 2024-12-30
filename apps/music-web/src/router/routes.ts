import type { RouteRecordRaw } from 'vue-router'
import { MusicHome, MusicMain } from '@pkmer-music/web/pages'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: '首页布局',
    component: MusicHome,
    children: [
      { path: '/', name: '音乐主体', component: MusicMain },
      {
        path: '/singers',
        name: '歌手列表',
        component: () => import('@pkmer-music/web/pages/singer/SingerStyles.vue')
      },
      {
        path: '/login',
        name: '登录注册页面',
        component: () => import('@pkmer-music/web/pages/login/SignIn.vue')
      }
    ]
  }
]

export default routes
