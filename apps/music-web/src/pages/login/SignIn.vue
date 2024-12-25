<script lang="ts" setup>

import { computed, ref, type CSSProperties } from 'vue'
const isLogin = ref(true)


const formMoveStyle = computed<CSSProperties>(() => {
  return {
    transform: `translateX(${isLogin.value ? '0' : '100%'})`
  }
})

const overlayMoveStyle = computed<CSSProperties>(() => {
  return {
    transform: `translateX(${isLogin.value ? '0' : '-100%'})`
  }
})

</script>

<template>
  <div class="form-container__wrapper">

    <section :style="formMoveStyle" class="relative w-1/2 h-fit">
      <div v-if="isLogin" class="login-container absolute w-full h-[300px] bg-red-400">登录</div>
      <div v-else class="absolute w-full h-[300px] bg-green-400">注册</div>
    </section>

    <!-- overlay start -->
    <section :style="overlayMoveStyle" class="over-container absolute left-1/2 w-[50%] h-[100px] z-10">
      <div class="relative w-[200%] bg-blue-500">
        <button v-if="isLogin" @click="isLogin = false">注册</button>
        <button v-else @click="isLogin = true">登录</button>
      </div>
    </section>
    <!-- overlay end -->
  </div>
</template>

<style lang="scss" scoped>
.form-container__wrapper {
  margin: 10px auto;
  position: relative;
  width: 650px;
  height: 450px;
  background-color: #ccc;
  overflow: hidden;


  /* .over-container {
    transform: translateX(-100%);
  } */
}
</style>
