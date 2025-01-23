import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'
import tailwind from 'tailwindcss'
import autoprefixer from 'autoprefixer'

// https://vite.dev/config/
export default defineConfig(({ mode }) => {
  const proxy =
    mode === 'development'
      ? {
          '/api': {
            target: 'http://localhost:8080', // 代理目标
            changeOrigin: true, // 修改请求头的 Origin
            rewrite: (path: string) => path.replace(/^\/api/, '') // 去掉 /api 前缀
          }
        }
      : undefined

  return {
    plugins: [
      vue({
        template: {
          compilerOptions: {
            // treat all tags with a dash as custom elements
            isCustomElement: tag => tag.includes('iconify-icon')
          }
        }
      }),
      vueJsx(),
      vueDevTools()
    ],
    css: {
      postcss: {
        plugins: [tailwind(), autoprefixer()]
      }
    },
    resolve: {
      alias: [
        {
          find: '@pkmer-music/web',
          replacement: fileURLToPath(new URL('./src', import.meta.url))
        }
      ]
    },
    server: {
      proxy
    }
  }
})
