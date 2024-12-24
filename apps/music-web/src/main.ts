import './assets/main.css'
import 'iconify-icon'
import { createApp } from 'vue'
import router from '@router'
import App from '@/App.vue'

const app = createApp(App).use(router)

if ('app' in window) {
  app.mount(window['app']! as HTMLDivElement)
}
