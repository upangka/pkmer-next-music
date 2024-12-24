<script lang="ts" setup>

const props = defineProps<{
  msg?: string,
  name: 'pkmer' | 'hzz'
}>()

function handleClick() {
  console.log("更新了吗???")
}

</script>

<template>
  <div class="hello-world">
    <span class="text-green-600">hello world</span> {{ props.msg }} {{ props.name }}
    <button @click="handleClick">click Me</button>
  </div>
</template>

<style lang="css" scoped>
.hello-world {
  color: red;
}
</style>
