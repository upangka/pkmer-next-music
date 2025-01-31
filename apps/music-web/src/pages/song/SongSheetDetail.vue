<script setup lang="ts">
import { onMounted } from 'vue'
import { getSongListDetail } from '@pkmer-music/web/api'
const props = defineProps({
  id: String
})

onMounted(async () => {
  console.log('song sheet detail', props.id)
  if (props.id) {
    const data = await getSongListDetail(props.id)
    console.log(data)
  }
})
</script>
<template></template>
