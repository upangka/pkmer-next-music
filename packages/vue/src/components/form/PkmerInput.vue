<script lang="ts">
interface Props {
  type?: 'text' | 'textarea'
  placeholder?: string
}
</script>
<script setup lang="ts">
import { computed } from 'vue'

const model = defineModel({ type: String })

const props = withDefaults(defineProps<Props>(), {
  type: 'text'
})

const isShowText = computed(() => props.type === 'text')
</script>
<template>
  <div class="flex flex-col gap-3">
    <header class="flex items-center justify-start gap-2">
      <slot name="header"></slot>
    </header>
    <!-- text start -->
    <template v-if="isShowText">
      <input
        v-model="model"
        :type="type"
        :placeholder="placeholder"
        class="rounded-md border border-gray-300 p-2"
      />
    </template>
    <!-- text end -->
    <!-- textarea start -->
    <template v-else>
      <textarea
        v-model="model"
        :placeholder="placeholder"
        class="resize-none rounded-md border border-gray-300 p-2 focus:border focus:border-green-600 focus:outline-none"
      ></textarea>
    </template>
    <!-- textarea end -->
  </div>
</template>
