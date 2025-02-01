<script lang="ts">
import type { TableColumnProps } from './types'
</script>

<script setup lang="ts">
import { columnCompName } from './constants'
defineOptions({
  name: columnCompName
})

/**
 * 在 Vue 3 中，withDefaults(defineProps(), {...})
 * 是编译时的语法糖，用来在子组件中提供属性的默认值。
 * 但这些默认值只在编译时解析，不会直接反映在运行时的 slot.props 中。
 * 因此，在父组件中通过 useSlots() 无法直接读取这些默认属性值。
 */
withDefaults(defineProps<TableColumnProps>(), {
  align: 'left',
  width: '1fr'
})
</script>
<template>
  <!-- 空模板，仅用于传递配置 -->
</template>
