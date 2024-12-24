import { defineConfig } from 'vite'
import { resolve } from 'node:path'
import { readdirSync } from 'node:fs'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

function getComponentEntries() {
  // 获取 src/components 目录下的所有子目录（组件）
  const componentsDir = resolve(__dirname, 'src/components')
  const componentDirs = readdirSync(componentsDir, { withFileTypes: true })
    .filter(dir => dir.isDirectory())
    .map(dir => dir.name)

  // 动态生成入口文件配置
  const entries: Record<string, string> = {
    index: resolve(__dirname, 'src/index.ts')
  }

  componentDirs.forEach(component => {
    entries[component] = resolve(componentsDir, component, 'index.ts')
  })

  return [entries, componentDirs] as const
}

export default defineConfig(() => {
  const [entries, componentDirs] = getComponentEntries()
  return {
    plugins: [vue(), vueJsx()],
    resolve: {
      dedupe: ['vue', '@vue/runtime-core']
    },
    build: {
      lib: {
        name: 'music-vue',
        fileName: (_format, name) => {
          if (componentDirs.includes(name)) {
            return `components/${name}/index.js`
          }
          return `${name}.js`
        },
        entry: entries
      },
      rollupOptions: {
        external: ['vue'],
        output: [
          {
            dir: 'dist/esm',
            format: 'esm',
            preserveModules: true
          }
        ]
      },
      minify: false
      // sourcemap: true
    }
  }
})
