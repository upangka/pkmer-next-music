import type { RouteRecordRaw } from 'vue-router'
import { MusicHome, MusicMain } from '@pages'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: '首页布局',
    component: MusicHome,
    children: [{ path: '/', name: '音乐主体', component: MusicMain }],
  },
]

export default routes
