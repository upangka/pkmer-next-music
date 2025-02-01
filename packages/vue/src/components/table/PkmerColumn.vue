<script lang="ts">
import type { TableColumnProps } from './types'
</script>

<script setup lang="ts">
import { columnCompName } from './constants'
defineOptions({
  name: columnCompName
})

withDefaults(defineProps<TableColumnProps>(), {
  align: 'left',
  width: '1fr'
})
</script>
<template>
  <!-- 空模板，仅用于传递配置 -->
</template>
