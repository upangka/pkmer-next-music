import './assets/main.css'
import 'iconify-icon'
import { createApp } from 'vue'
import router from '@pkmer-music/web/router'
import App from '@pkmer-music/web/App.vue'
import '@pkmer-music-ui/vue/theme.css'
const app = createApp(App).use(router)

if ('app' in window) {
  app.mount(window['app']! as HTMLDivElement)
}
