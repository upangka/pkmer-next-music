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
withDefaults(defineProps<Props>(), {
  hoverColor: '#f5f5f5'
})
const hoverRow = ref(-1)
const slots = useSlots()

const normalizedColumns = computed<TableColumnProps[]>(() => {
  return (
    slots
      .default?.({})
      .flatMap((slot: any) => {
        return (slot.children as any[])?.map(child => {
          if (child.type?.name === columnCompName) {
            if (child.props) {
              const childProps = child.props as TableColumnProps
              return {
                prop: childProps.prop,
                label: childProps.label,
                width: childProps.width,
                align: childProps.align
              }
            }
            return null
          }
        })
      })
      .filter(Boolean) || []
  )
})

const gridStyle = computed<CSSProperties>(() => ({
  gridTemplateColumns: normalizedColumns.value.map(col => col.width).join(' ')
}))

const getColStyle = (col: TableColumnProps): CSSProperties => {
  return {
    textAlign: col.align
  }
}
</script>
<template>
  <div class="table-grid__container">
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
  }
  .table-header__container {
    display: grid;
    .header-cell {
      @extend .share-cell;
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
