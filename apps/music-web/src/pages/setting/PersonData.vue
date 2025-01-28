<script setup lang="ts">
import { reactive } from 'vue'
import { FormButton, FormInput } from '@pkmer-music/web/components'
import { PkmerRadioGroup, PkmerRadio } from '@pkmer-music-ui/vue/radio'
import { PkmerDatePicker } from '@pkmer-music-ui/vue/date'
import { PkmerSelector, PkmerInput } from '@pkmer-music-ui/vue/form'
import { AREA } from '@pkmer/libs/constants'

const person = reactive({
  sex: '',
  birthDay: '',
  address: '',
  introduction: ''
})
</script>
<template>
  <section>
    <form>
      <FormInput icon="bxs:user" name="用户名" label="用户名" placeholder="请输入用户名" />
      <PkmerRadioGroup v-model="person.sex">
        <template #header>性别</template>
        <PkmerRadio value="0">男</PkmerRadio>
        <PkmerRadio value="1">女</PkmerRadio>
        <PkmerRadio value="2">保密</PkmerRadio>
      </PkmerRadioGroup>
      <PkmerDatePicker v-model="person.birthDay">
        <template #header>生日</template>
      </PkmerDatePicker>

      <!-- 地区start -->
      <PkmerSelector v-model="person.address" :options="AREA">
        <template #header>地区</template>
      </PkmerSelector>
      <!-- 地区end -->

      <!-- 签名start -->
      <PkmerInput v-model="person.introduction" type="textarea" placeholder="个性签名">
        <template #header>个性签名</template>
      </PkmerInput>
      <!-- 签名end -->
    </form>
  </section>
</template>
