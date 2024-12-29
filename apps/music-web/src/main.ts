import { createApp } from 'vue'
import { createPinia } from 'pinia'
import 'iconify-icon'
import './assets/main.css'
import router from '@pkmer-music/web/router'
import App from '@pkmer-music/web/App.vue'
import '@pkmer-music-ui/vue/theme.css'
const pinia = createPinia()

const app = createApp(App).use(router).use(pinia)

if ('app' in window) {
  app.mount(window['app']! as HTMLDivElement)
}
