<script setup lang="ts">
import { reactive, type CSSProperties } from 'vue'
const state = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})

const btnStyle: CSSProperties = {
  padding: '10px 20px',
  borderRadius: '5px'
}

import { FormInput, FormButton } from '@pkmer-music/web/components'
</script>
<template>
  <section
    class="password-container mx-auto flex w-[60vw] items-center justify-center border-2 border-black p-3"
  >
    <form class="w-[40%] p-5">
      <FormInput
        class="item"
        v-model="state.oldPassword"
        icon="formkit:password"
        placeholder="旧密码"
        type="password"
        name="password"
      />
      <FormInput
        class="item"
        v-model="state.newPassword"
        icon="formkit:password"
        placeholder="新密码"
        type="password"
        name="password"
      />
      <FormInput
        class="item"
        v-model="state.confirmPassword"
        icon="formkit:password"
        placeholder="确认密码"
        type="password"
        name="password"
      />
      <!-- 按钮start -->
      <div class="flex items-center justify-evenly gap-[150px]">
        <FormButton :style="btnStyle">重置</FormButton>
        <FormButton :style="btnStyle">确认</FormButton>
      </div>
      <!-- 按钮end -->
    </form>
  </section>
</template>
<style lang="scss" scoped>
.password-container {
  .item {
    margin-bottom: 30px;
  }
}
</style>
