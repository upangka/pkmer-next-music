# Description

> 采用turborepo来管理两个前端项目，一个用户端(music-web)，一个管理端(music-management)

相关组件抽离在

- Vue相关组件抽离在[packages/vue](./packages/vue/)包中
- React相关组件抽离在[packages/react](./packages/react/)包中

# 用户界面

Vue3 + Vite开发
[music-web](./apps/music-web/)

# 管理端

React + NextJS开发
[music-management](./apps/music-management/)

# API 接口

- springboot(3.4.1)

[music api](./apps/music-api/)

# 常用命令

```sh
# 启动vue
pnpm dev --filter=@pkmer-music-ui/vue --filter=music-web
```

# packages

- [libs](./packages/libs/) 公共库
- [react](./packages/react/) react组件库
- [vue](./packages/vue/) vue组件库
