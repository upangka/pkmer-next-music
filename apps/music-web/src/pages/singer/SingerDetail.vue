<script lang="ts">
import { Singer } from '@pkmer-music/web/types'
interface Props {
  singerId: string
}
</script>
<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { getSingerDetail } from '@pkmer-music/web/api'
const props = defineProps<Props>()

const singer = ref<Singer>()
onMounted(async () => {
  singer.value = await getSingerDetail(props.singerId)
})
</script>
<template>
  {{ singer?.name }}
</template>
