import { defineConfig } from 'vite'
import { resolve } from 'node:path'
import { readdirSync } from 'node:fs'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import dts from 'vite-plugin-dts'
import tailwind from 'tailwindcss'
import autoprefixer from 'autoprefixer'

/**
 * 生成组件的动态入口文件配置
 */
function getComponentEntries() {
  // 获取 src/components 目录下的所有子目录（组件）
  const componentsDir = resolve(__dirname, 'src/components')
  const componentDirs = readdirSync(componentsDir, { withFileTypes: true })
    .filter(dir => dir.isDirectory())
    .map(dir => dir.name)

  // 动态生成入口文件配置
  const entries: Record<string, string> = {
    index: resolve(__dirname, 'src/index.ts'),
    components: resolve(__dirname, 'src/components/index.ts')
  }
  console.log(componentDirs)
  componentDirs.forEach(component => {
    entries[component] = resolve(componentsDir, component, 'index.ts')
  })

  return [entries, componentDirs] as const
}

export default defineConfig(() => {
  const [entries, componentDirs] = getComponentEntries()
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
      dts({
        tsconfigPath: resolve(__dirname, 'tsconfig.build.json'),
        outDir: 'dist/types',
        cleanVueFileName: true // 清除 .vue 后缀
      })
    ],
    css: {
      postcss: {
        plugins: [tailwind(), autoprefixer()]
      }
    },
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
          if (name === 'components') {
            return `components/index.js`
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
            preserveModules: true,
            assetFileNames(chunkInfo) {
              if (chunkInfo.names[0] === 'vue.css') {
                return 'index.css'
              }
              return 'assets/[name].[hash][extname]'
            }
          }
        ]
      },
      minify: false
      // sourcemap: true
    }
  }
})
