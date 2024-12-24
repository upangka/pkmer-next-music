<script lang="ts" setup>
function handleClick() {
  console.log("See me???")
}
</script>

<template>
  <div class="hello-world">
    Avatar
    <button @click="handleClick">click Me</button>
  </div>
</template>

<style lang="css" scoped>
.hello-world {
  color: red;
}
</style>
