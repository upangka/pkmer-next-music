<script lang="ts" setup>
</script>

<template>
  <section class="assider-container">
    当前播放
  </section>
</template>

<style lang="scss" scoped>
@import "@pkmer-music/web/assets/styles/__variables.scss";

.assider-container {
  position: fixed;
  top: $header-height;
  bottom: 0;
  right: 0;
  background-color: red;
  width: 300px;
  z-index: $music-assider-z-index;
}
</style>
