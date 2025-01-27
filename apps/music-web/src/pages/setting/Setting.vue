<script setup lang="ts">
import { PkmerTabPanne, PkmerTabs } from '@pkmer-music-ui/vue/tabs'
import Password from './Password.vue'
import PersonData from './PersonData.vue'
</script>

<template>
  <section class="mx-auto my-5 h-fit w-3/4">
    <header class="mb-5 border-b-4 border-b-black py-3 text-2xl font-bold">设置</header>
    <PkmerTabs tab-position="left">
      <PkmerTabPanne label="个人资料" name="first">
        <PersonData />
      </PkmerTabPanne>
      <PkmerTabPanne label="更改密码" name="second">
        <Password />
      </PkmerTabPanne>
      <PkmerTabPanne label="账号和安全" name="third"> 3</PkmerTabPanne>
    </PkmerTabs>
  </section>
</template>
