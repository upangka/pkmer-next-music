<script setup lang="ts">
import { reactive } from 'vue'
import { FormButton, FormInput } from '@pkmer-music/web/components'
import { PkmerRadioGroup, PkmerRadio } from '@pkmer-music-ui/vue/radio'
import { PkmerDatePicker } from '@pkmer-music-ui/vue/date'
const person = reactive({
  sex: '',
  birthDay: ''
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
    </form>
  </section>
</template>
