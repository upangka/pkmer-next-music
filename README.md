# Description

> 采用turborepo来管理两个前端项目，一个用户端(music-web)，一个管理端(music-management)

相关组件抽离在

- Vue相关组件抽离在[packages/vue](./packages/vue/)包中
- React相关组件抽离在[packages/react](./packages/react/)包中

# 用户界面

Vue3 + Vite开发

# 管理端

React + NextJS开发

# 常用命令

```sh
# 启动vue
pnpm dev --filter=@pkmer-music-ui/vue --filter=music-web
```
