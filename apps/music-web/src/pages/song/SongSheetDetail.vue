<script setup lang="ts">
import { onMounted } from 'vue'

const props = defineProps({
  id: String
})

onMounted(() => {
  console.log('song sheet detail', props.id)
})
</script>
<template></template>
