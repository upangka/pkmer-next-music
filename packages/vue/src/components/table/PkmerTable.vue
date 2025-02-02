<script lang="ts">
import type { TableColumnProps } from './types'
interface Props {
  data: Array<any>
  hoverColor?: string
}
</script>
<script setup lang="ts">
import { ref, useSlots, computed, type CSSProperties } from 'vue'
import { columnCompName } from './constants'

const emits = defineEmits<{
  rowClick: [row: any]
}>()

withDefaults(defineProps<Props>(), {
  hoverColor: '#f5f5f5'
})
const hoverRow = ref(-1)
const slots = useSlots()

const normalizedColumns = computed<TableColumnProps[]>(() => {
  return (
    slots
      .default?.({})
      .map((slot: any) => {
        if (slot.type?.name === columnCompName) {
          if (slot.props) {
            const childProps = slot.props as TableColumnProps
            const value = {
              prop: childProps.prop!,
              label: childProps.label!,
              width: childProps.width || '1fr', // 设置默认值
              align: childProps.align || 'left' // 设置默认值
            }
            return value
          }
        }
        return null
      })
      .filter(Boolean) || []
  )
})

const gridStyle = computed<CSSProperties>(() => {
  const value = normalizedColumns.value.map(col => col.width).join(' ')
  console.log({ value })
  return {
    gridTemplateColumns: value
  }
})

const getColStyle = (col: TableColumnProps): CSSProperties => {
  return {
    textAlign: col.align
  }
}
</script>
<template>
  <div class="table-grid__container rounded-md shadow-lg">
    <!-- 表头start -->
    <div class="table-header__container" :style="gridStyle">
      <div
        v-for="(col, index) in normalizedColumns"
        :key="index"
        :style="getColStyle(col)"
        class="header-cell"
      >
        {{ col.label }}
      </div>
    </div>
    <!-- 表头end -->
    <!-- 表格内容start -->
    <section class="table-content__container">
      <div
        v-for="(row, rowIndex) in data"
        :key="rowIndex"
        class="grid-row"
        :style="gridStyle"
        @mouseenter="hoverRow = rowIndex"
        @mouseleave="hoverRow = -1"
        @click="emits('rowClick', row)"
      >
        <div
          v-for="(col, colIndex) in normalizedColumns"
          :key="colIndex"
          :style="[getColStyle(col), { backgroundColor: hoverRow === rowIndex ? hoverColor : '' }]"
          class="data-cell"
        >
          {{ row[col.prop] }}
        </div>
      </div>
    </section>
    <!-- 表格内容end -->
  </div>
  <!-- 收集子组件的配置 -->
  <slot></slot>
</template>
<style lang="scss" scoped>
.table-grid__container {
  .share-cell {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    line-height: 1.5;
    padding: 10px 0;
    border-bottom: 1px solid #cbcbcb;
  }
  .table-header__container {
    display: grid;

    .header-cell {
      @extend .share-cell;
      font-weight: 700;
    }
  }

  .table-content__container {
    .grid-row {
      display: grid;
      transition: background-color 0.3s ease;
      .data-cell {
        @extend .share-cell;
      }
    }
  }
}
</style>
