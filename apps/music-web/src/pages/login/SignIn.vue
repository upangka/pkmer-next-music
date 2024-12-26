<script lang="ts" setup>
import bgs from "./bgs"
// import loginBg from "@pkmer-music/web/assets/imgs/login-bg.jpg"
import { computed, ref, type CSSProperties } from 'vue'
const isLogin = ref(true)


const loginBg = bgs[Math.floor(Math.random() * bgs.length)]

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
    <div :class="['form-container__wrapper', isLogin ? '' : 'move-to__right']">
      <!-- 登录表达start -->
      <section :style="formMoveStyle" class="move-transition relative w-1/2 h-fit">
        <div class="login-container absolute w-full h-[300px] bg-red-400 z-2">登录</div>
        <div class="signup-container absolute w-full h-[300px] bg-green-400 opacity-0 z-1">注册</div>
      </section>
      <!-- 登录表达end -->
      <!-- overlay start -->
      <section :style="[overlayMoveStyle]"
        class="move-transition over-container absolute left-1/2 w-[50%] h-full overflow-hidden">
        <div :style="bgStyle" class="relative w-[200%] bg-transparent h-full">
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
  background-color: transparent;

  .signup-container {
    animation: hidden-signup 0.6s;

    @keyframes hidden-signup {

      0%,
      49.99% {
        opacity: 1;
        z-index: 5;
      }

      50%,
      100% {
        opacity: 0;
        z-index: 1;
      }
    }
  }

  &.move-to__right {
    .signup-container {
      opacity: 1;
      z-index: 5;
      animation: show 0.6s;

      @keyframes show {
        0% {
          opacity: 0;
          z-index: 1;
        }

        49.99% {
          opacity: 0;
          z-index: 1;
        }

        50%,
        100% {
          opacity: 1;
          z-index: 5;
        }
      }
    }
  }



  button {
    background-color: antiquewhite;
    border: 1px solid black;
    color: black;
    padding: 10px 20px;
    border-radius: 15px;
  }
}

.move-transition {
  transition: all 0.6s ease-in-out;
}
</style>
