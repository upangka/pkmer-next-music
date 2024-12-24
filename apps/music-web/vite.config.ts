import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueJsx(), vueDevTools()],
  resolve: {
    alias: [
      {
        find: '@',
        replacement: fileURLToPath(new URL('./src', import.meta.url))
      },
      {
        find: '@pages',
        replacement: fileURLToPath(new URL('./src/pages', import.meta.url))
      },
      {
        find: '@components',
        replacement: fileURLToPath(new URL('./src/components', import.meta.url))
      },
      {
        find: '@router',
        replacement: fileURLToPath(new URL('./src/router', import.meta.url))
      },
      {
        find: '@stores',
        replacement: fileURLToPath(new URL('./src/stores', import.meta.url))
      },
      {
        find: '@utils',
        replacement: fileURLToPath(new URL('./src/utils', import.meta.url))
      }
    ]
  }
})
