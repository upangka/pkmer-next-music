<script lang="ts">
interface Props {
  value: string
}
</script>

<script setup lang="ts">
import { inject } from 'vue'
import { radioKey } from './constants'
const props = defineProps<Props>()

const context = inject(radioKey)!

function handleChange() {
  context.onChange(props.value)
}
</script>

<template>
  <label>
    <input
      type="radio"
      :value="props.value"
      @change="handleChange"
      :checked="context.modelValue.value === props.value"
    />
    <span class="mx-2">
      <slot></slot>
    </span>
  </label>
</template>
