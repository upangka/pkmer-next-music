<script setup lang="ts">
import { provide, onMounted, ref } from 'vue'
import { radioKey } from './constants'

const model = defineModel({ type: String, default: '' })
const contentRef = ref<HTMLDivElement>()
provide(radioKey, {
  modelValue: model,
  onChange: changeModel
})

onMounted(() => {
  if (contentRef.value) {
    const children = contentRef.value.querySelectorAll('input[type="radio"]')
    if (children.length > 0) {
      const inputRadio = children[0] as HTMLInputElement
      inputRadio.checked = true
      changeModel(inputRadio.value)
    }
  }
})

function changeModel(value: string) {
  model.value = value
}
</script>
<template>
  <div class="flex flex-col gap-2">
    <header>
      <slot name="header"></slot>
    </header>
    <section class="flex items-center justify-start gap-2" ref="contentRef">
      <slot></slot>
    </section>
  </div>
</template>
