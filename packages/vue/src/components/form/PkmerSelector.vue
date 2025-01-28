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
import { ref } from 'vue'
import PkmerIcon from '../icon/PkmerIcon.vue'
const model = defineModel({ type: String })
const props = defineProps<Props>()
const isShowOptions = ref(false)

function handleSelect(value: string) {
  model.value = value
  isShowOptions.value = false
}
</script>
<template>
  <section class="flex flex-col gap-3">
    <header><slot name="header"></slot></header>
    <!-- 内容展示start -->
    <section>
      <!-- 选中的内容start -->
      <div
        @click="isShowOptions = !isShowOptions"
        class="mb-3 flex cursor-pointer items-center justify-between rounded-md border border-gray-500"
      >
        <span class="inline-block flex-1 text-center">{{ model }}</span>
        <PkmerIcon v-if="isShowOptions" class="flex-0" icon="lsicon:up-outline" />
        <PkmerIcon v-else class="flex-0" icon="lsicon:down-outline" />
      </div>
      <!-- 选中的内容end -->

      <!-- options start -->
      <ul
        class="flex h-[200px] flex-col gap-2 overflow-y-scroll rounded-md bg-black text-white shadow-lg"
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
