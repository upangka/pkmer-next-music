<script lang="ts" setup>
import loginBg from "@pkmer-music/web/assets/imgs/login-bg.jpg"
import { computed, ref, type CSSProperties } from 'vue'
const isLogin = ref(true)


const bgStyle = {
  backgroundImage: `url(${loginBg})`,
  backgroundRepeat: 'no-repeat',
  backgroundSize: 'cover',
  backgroundPosition: 'center',
  backgroundAttachment: 'fixed'
} satisfies CSSProperties

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
  <div :style="bgStyle" class="bg-red-500 h-[600px] flex justify-center items-center">
    <div class="form-container__wrapper">
      <section :style="formMoveStyle" class="relative w-1/2 h-fit z-10">
        <div v-if="isLogin" class="login-container absolute w-full h-[300px] bg-red-400">登录</div>
        <div v-else class="absolute w-full h-[300px] bg-green-400">注册</div>
      </section>

      <!-- overlay start -->
      <section :style="overlayMoveStyle" class="over-container absolute left-1/2 w-[50%] h-full">
        <div class="relative w-[200%] bg-blue-500 h-full">
          <div class="w-1/2 h-full flex justify-center items-center">
            <button v-if="isLogin" @click="isLogin = false">注册</button>
            <button v-else @click="isLogin = true">登录</button>
          </div>
        </div>
      </section>
      <!-- overlay end -->
    </div>
  </div>

</template>

<style lang="scss" scoped>
.form-container__wrapper {
  margin: 10px auto;
  position: relative;
  width: 650px;
  height: 300px;
  background-color: #ccc;
  overflow: hidden;


  button {
    background-color: antiquewhite;
    border: 1px solid black;
    color: black;
    padding: 10px 20px;
    border-radius: 15px;
  }
}
</style>
