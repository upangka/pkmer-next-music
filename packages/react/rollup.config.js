import { defineConfig } from 'rollup'
import { nodeResolve } from '@rollup/plugin-node-resolve'
import { resolve } from 'node:path'
import { pathToFileURL } from 'node:url'
import esbuild from 'rollup-plugin-esbuild'
import alias from '@rollup/plugin-alias'
import tsconfigJson from './tsconfig.json' with { type: 'json' }

const dir = process.cwd()
const tsconfigPath = resolve(dir, 'tsconfig.json')

/**
 * 获取别名的处理
 * 注意tsconfig中的配置不能有注释
 * @returns
 */
async function getAliasEntries() {
  // const tsconfigJson = await import(pathToFileURL(tsconfigPath).href, {
  //   assert: { type: 'json' }
  // })
  const aliasEntries = []

  const entries = Object.entries(tsconfigJson.compilerOptions.paths || {})

  for (const [key, value] of entries) {
    aliasEntries.push({
      find: key.replace('/*', ''),
      replacement: resolve(dir, value[0].replace('/*', ''))
    })
  }
  return aliasEntries
}

const config = defineConfig(async () => {
  const entries = await getAliasEntries()
  return {
    input: 'src/index.ts',
    output: [
      {
        dir: 'dist',
        format: 'esm',
        preserveModules: true
      }
    ],
    plugins: [
      nodeResolve({ extensions: ['.ts', '.tsx', '.js', '.jsx'] }),
      alias({
        entries
      }),
      esbuild({
        sourceMap: true, // 打包后是否生成sourcemap
        tsconfig: tsconfigPath, // tsconfig.json路径
        platform: 'browser',
        jsx: 'automatic', // 很重要
        minify: false
      })
    ],
    external: ['react', 'react-dom', 'react/jsx-runtime'],
    onwarn: (msg, warn) => {
      if (msg.code === 'SOURCEMAP_ERROR') return
      if (msg.code === 'MODULE_LEVEL_DIRECTIVE') return
    }
  }
})
export default config
