<script lang="ts" setup>
import bgs from './bgs'
import { computed, ref, reactive, type CSSProperties, watch } from 'vue'
import { useRouter } from 'vue-router'
import { login } from '@pkmer-music/web/api/user'
import { useUserCenterStore } from '@pkmer-music/web/stores'
import { FormButton, FormInput } from '@pkmer-music/web/components'

const userCenter = useUserCenterStore()
const router = useRouter()
const isLogin = ref(true)
const loginWaiting = ref(false)
const firstTimeLoad = ref(true)
const loginBg = bgs[Math.floor(Math.random() * bgs.length)]

const state = reactive({
  username: '',
  email: '',
  password: ''
})

const stopWatch = watch(isLogin, () => {
  if (firstTimeLoad.value) {
    firstTimeLoad.value = false
    stopWatch()
  }
})

const bgStyle = {
  backgroundImage: `url(${loginBg})`,
  backgroundRepeat: 'no-repeat',
  backgroundSize: 'cover',
  backgroundPosition: 'center',
  backgroundAttachment: 'fixed'
} satisfies CSSProperties

const formMoveStyle = computed<CSSProperties>(() => {
  return {
    transform: `translateX(${isLogin.value ? '0' : '101%'})`
  }
})

const overlayMoveStyle = computed<CSSProperties>(() => {
  return {
    transform: `translateX(${isLogin.value ? '0' : '-101%'})`
  }
})

async function handleLogin(e: Event) {
  loginWaiting.value = true
  const user = await login(state.email, state.password)

  if (user) {
    localStorage.setItem('token', user.token)
    loginWaiting.value = false
    userCenter.setUser(user)
    router.push('/')
  }
}

function handleRegister() {
  console.log(state.email, state.password)
}

// 重置 state 的函数
function resetState() {
  state.email = ''
  state.password = ''
  state.username = ''
}

function switchToLogin() {
  isLogin.value = true
  resetState()
}

function switchRegister() {
  isLogin.value = false
  resetState()
}
</script>

<template>
  <div :style="bgStyle" class="login-and-sign__container">
    <div :class="['form-container__wrapper', isLogin ? '' : 'move-to__right']">
      <!-- 表单start -->
      <section :style="formMoveStyle" class="move-transition form-container">
        <!-- 登录表单start -->
        <form class="login-container" @submit.prevent="handleLogin">
          <h1 class="p-4 text-center text-xl">登录</h1>
          <FormInput v-model="state.email" icon="uil:user" placeholder="用户名" name="username" />
          <FormInput
            v-model="state.password"
            icon="formkit:password"
            placeholder=" 密码"
            type="password"
            name="password"
          />
          <div class="flex justify-center">
            <FormButton>
              <span v-if="loginWaiting">登录中...</span>
              <span v-else>登录</span>
            </FormButton>
          </div>
        </form>
        <!-- 登录表单end -->
        <!-- 注册表单start -->
        <form
          :style="{ animationDuration: firstTimeLoad ? '0s' : '0.6s' }"
          class="signup-container"
          @submit.prevent="handleRegister"
        >
          <h1 class="title">注册</h1>
          <FormInput
            v-model="state.username"
            icon="uil:user"
            placeholder="用户名"
            name="username"
          />
          <FormInput
            v-model="state.email"
            icon="iconamoon:email-thin"
            placeholder="邮箱"
            name="email"
          />
          <FormInput
            v-model="state.password"
            icon="formkit:password"
            placeholder=" 密码"
            type="password"
            name="password"
          />
          <div class="flex justify-center">
            <FormButton>注册</FormButton>
          </div>
        </form>
        <!-- 注册表单end -->
      </section>
      <!-- 登录表达end -->
      <!-- overlay start -->
      <section :style="[overlayMoveStyle]" class="move-transition over-container">
        <div :style="bgStyle" class="inner-overlay__container">
          <div class="absolute h-full w-1/2">
            <div v-if="isLogin" class="register-btn__container">
              <FormButton @click="switchRegister">注册</FormButton>
            </div>
            <div v-else className="login-btn__container">
              <FormButton @click="switchToLogin">登录</FormButton>
            </div>
          </div>
        </div>
      </section>
      <!-- overlay end -->
    </div>
  </div>
</template>

<style lang="scss" scoped>
$bgColor: #e9e9e9;
$formHeight: 650px;

.login-and-sign__container {
  display: flex;
  height: $formHeight;
  justify-content: center;
  align-items: center;

  .form-container__wrapper {
    margin: 10px auto;
    position: relative;
    width: 650px;
    height: 400px;
    border-radius: 8px;
    background-color: transparent;
    background-color: $bgColor;
    overflow: hidden;

    .form-container {
      position: relative;
      height: fit-content;
      width: 50%;

      .container-share {
        position: absolute;
        height: $formHeight;
        width: 100%;

        .title {
          padding: 16px;
          text-align: center;
          font-size: 20px;
          line-height: 28px;
        }
      }

      .login-container {
        @extend .container-share;
        z-index: 2;
        background-color: $bgColor;
      }

      .signup-container {
        @extend .container-share;
        opacity: 0;
        // 初始状态加载，通过js控制时间
        animation: hidden-signup;
        z-index: 1;
        background-color: $bgColor;

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
    }

    .over-container {
      position: absolute;
      left: 50%;
      width: 50%;
      height: 100%;
      overflow: hidden;

      .inner-overlay__container {
        position: relative;
        height: 100%;
        width: 200%;
        background-color: transparent;
        transform: translateX(-50%);
        transition: all 0.6s ease-in-out;

        .bth__container {
          position: absolute;
          top: 0;
          width: 100%;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
        }

        .register-btn__container {
          @extend .bth__container;
          right: -100%;
        }

        .login-btn__container {
          @extend .bth__container;
          left: 0%;
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

      .inner-overlay__container {
        transform: translateX(0%);
      }
    }
  }

  .move-transition {
    transition: all 0.6s ease-in-out;
  }
}
</style>
