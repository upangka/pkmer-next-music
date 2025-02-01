<script lang="ts">
interface User {
  id: number
  name: string
  age: number
  email: string
}
</script>
<script setup lang="ts">
import { PkmerTable, PkmerColumn } from '@pkmer-music-ui/vue/table'

// 表格数据
const users: User[] = [
  { id: 1, name: '张三', age: 25, email: 'zhangsan@example.com' },
  { id: 2, name: '李四', age: 30, email: 'lisi@example.com' },
  { id: 3, name: '王五', age: 28, email: 'wangwu@example.com' }
]
</script>
<template>
  <PkmerTable :data="users" hover-color="green">
    <PkmerColumn prop="id" label="ID" width="100px" align="center" />
    <PkmerColumn prop="name" label="姓名" align="center" />
    <PkmerColumn prop="age" label="年龄" align="center" />
    <PkmerColumn prop="email" label="邮箱" align="center" />
  </PkmerTable>
</template>
