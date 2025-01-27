<script setup lang="ts">
import { reactive } from 'vue'
import { FormButton, FormInput, PkmerRadioGroup, PkmerRadio } from '@pkmer-music/web/components'
const person = reactive({
  sex: ''
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
    </form>
  </section>
</template>
