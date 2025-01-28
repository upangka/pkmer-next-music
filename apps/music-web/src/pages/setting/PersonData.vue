<script setup lang="ts">
import { reactive, onMounted, type CSSProperties } from 'vue'
import { useRouter } from 'vue-router'
import { FormButton, FormInput } from '@pkmer-music/web/components'
import { PkmerRadioGroup, PkmerRadio } from '@pkmer-music-ui/vue/radio'
import { PkmerDatePicker } from '@pkmer-music-ui/vue/date'
import { PkmerSelector, PkmerInput } from '@pkmer-music-ui/vue/form'
import { PkmerIcon } from '@pkmer-music-ui/vue'
import { AREA } from '@pkmer/libs/constants'
import { formatDate } from '@pkmer/libs/utils'

import { updateUserDetails, getUserDetails } from '@pkmer-music/web/api/user'
const router = useRouter()
const person = reactive({
  username: '',
  sex: '',
  birthDay: '',
  address: '',
  introduction: '',
  phone: '',
  email: ''
})

const btnStyle: CSSProperties = {
  padding: '0.3rem 1rem'
}

onMounted(async () => {
  const res = await getUserDetails()
  person.username = res.username
  person.sex = '' + res.sex
  person.birthDay = formatDate(new Date(res.birth + 'Z')) // 转换为本地时间
  person.address = res.location
  person.introduction = res.introduction
  person.phone = res.phoneNum
  person.email = res.email
})

async function handleConfig() {
  await updateUserDetails({
    username: person.username,
    sex: +person.sex,
    birth: `${person.birthDay}T00:00:00`, // 补充时间部分
    location: person.address,
    introduction: person.introduction,
    phoneNum: person.phone,
    email: person.email
  })

  router.push('/')
}
</script>
<template>
  <section class="mx-auto flex w-[60vw] items-center justify-center border border-black p-5">
    <form class="mx-auto flex w-1/2 flex-col gap-4" @submit.prevent="handleConfig">
      <!-- 用户名start -->
      <FormInput icon="bxs:user" v-model="person.username" name="username" placeholder="用户名" />
      <!-- 用户名end -->
      <!-- 性别start -->
      <PkmerRadioGroup v-model="person.sex">
        <template #header
          ><PkmerIcon icon="icons8:gender-neutral-user" /> <span>性别</span></template
        >
        <PkmerRadio value="0">男</PkmerRadio>
        <PkmerRadio value="1">女</PkmerRadio>
        <PkmerRadio value="2">保密</PkmerRadio>
      </PkmerRadioGroup>
      <!-- 性别end -->

      <!-- 生日start -->
      <PkmerDatePicker v-model="person.birthDay">
        <template #header
          ><PkmerIcon icon="arcticons:birthdayadapter" />
          <span>生日</span>
        </template>
      </PkmerDatePicker>
      <!-- 生日end -->

      <!-- 签名start -->
      <PkmerInput v-model="person.introduction" type="textarea" placeholder="个性签名">
        <template #header><PkmerIcon icon="ph:signature-bold" /><span>个性签名</span></template>
      </PkmerInput>
      <!-- 签名end -->

      <!-- 地区start -->
      <PkmerSelector v-model="person.address" :options="AREA">
        <template #header><PkmerIcon icon="ph:map-pin-area" /> <span>地区</span></template>
      </PkmerSelector>
      <!-- 地区end -->
      <!-- 手机号start -->
      <FormInput icon="proicons:phone" v-model="person.phone" name="phone" placeholder="手机号" />
      <!-- 手机号end -->

      <!-- 邮箱start -->
      <FormInput icon="line-md:email" v-model="person.email" name="email" placeholder="邮箱" />
      <!-- 邮箱end -->

      <section class="flex w-full justify-evenly gap-8">
        <FormButton :style="btnStyle">取消</FormButton>
        <FormButton type="submit" :style="btnStyle">确认</FormButton>
      </section>
    </form>
  </section>
</template>
