<script setup lang="ts">
import { onMounted } from 'vue'
import { formatDate } from '@pkmer/libs/utils'
const model = defineModel({ type: String })

onMounted(() => {
  model.value = formatDate(new Date())
})

function handleChange(e: Event) {
  const target = e.target as HTMLInputElement
  model.value = target.value
}
</script>
<template>
  <div class="flex flex-col gap-2">
    <header class="flex items-center justify-start gap-2">
      <slot name="header"></slot>
    </header>
    <label>
      <input class="border border-black" type="date" :value="model" @change="handleChange" />
    </label>
  </div>
</template>
