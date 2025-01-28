<script lang="ts">
interface Item {
  value: string
  label: string
}
interface Props {
  options: Item[]
}
</script>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import PkmerIcon from '../icon/PkmerIcon.vue'
const model = defineModel({ type: String })
const props = defineProps<Props>()
const isShowOptions = ref(false)

onMounted(() => (model.value = props.options[0].value))

function handleSelect(value: string) {
  model.value = value
  isShowOptions.value = false
}
</script>
<template>
  <section class="flex flex-col gap-3">
    <header class="flex items-center justify-start gap-2"><slot name="header"></slot></header>
    <!-- 内容展示start -->
    <section class="relative">
      <!-- 选中的内容start -->
      <div
        @click="isShowOptions = !isShowOptions"
        class="mb-3 flex w-full cursor-pointer items-center justify-between rounded-md border border-gray-500"
      >
        <span class="inline-block flex-1 text-center">{{ model }}</span>
        <PkmerIcon v-if="isShowOptions" class="flex-0" icon="lsicon:up-outline" />
        <PkmerIcon v-else class="flex-0" icon="lsicon:down-outline" />
      </div>
      <!-- 选中的内容end -->

      <!-- options start -->
      <ul
        class="absolute z-[9999] flex h-[200px] w-full flex-col gap-2 overflow-y-scroll rounded-md bg-black text-white shadow-lg"
        v-show="isShowOptions"
      >
        <li
          class="my-1 text-center hover:bg-green-600 hover:text-white"
          v-for="option in props.options"
          :key="option.label"
          @click="() => handleSelect(option.value)"
        >
          {{ option.label }}
        </li>
      </ul>
      <!-- options end -->
    </section>
    <!-- 内容展示end -->
  </section>
</template>
