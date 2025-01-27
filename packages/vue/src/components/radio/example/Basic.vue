<script setup lang="ts">
import { ref } from 'vue'
import { PkmerRadio, PkmerRadioGroup } from '..'
const sex = ref('')
</script>
<template>
  <PkmerRadioGroup v-model="sex">
    <template #header>性别</template>
    <PkmerRadio value="0">男</PkmerRadio>
    <PkmerRadio value="1">女</PkmerRadio>
    <PkmerRadio value="2">保密</PkmerRadio>
  </PkmerRadioGroup>
</template>
