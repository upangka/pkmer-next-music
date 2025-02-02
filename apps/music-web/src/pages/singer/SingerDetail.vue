<script lang="ts">
interface Props {
  singerId: string
}
</script>
<script lang="ts" setup>
import { onMounted } from 'vue'
const props = defineProps<Props>()

onMounted(() => {
  console.log(props)
})
</script>
<template></template>
