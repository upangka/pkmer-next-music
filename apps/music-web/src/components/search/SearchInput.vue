<script lang="ts" setup>
import { ref } from 'vue'
import { PkmerIcon } from "@pkmer-music-ui/vue/icon"

const inputFocusStatus = ref(true)
</script>

<template>
  <section class="relative">
    <PkmerIcon icon="iconamoon:search-thin" :height="25" :width="25" v-show="inputFocusStatus"
      class="text-1xl absolute left top-1/2 -translate-y-1/2 text-slate-500" />
    <input @focus="inputFocusStatus = false" @blur="inputFocusStatus = true" type="text"
      placeholder="search something..." class="w-80 rounded-md border border-gray-600 p-0.5 pl-6 focus:outline-none " />
  </section>
</template>

<style lang="scss" scoped></style>
