<script setup lang="ts">
import { PkmerTabPanne, PkmerTabs } from '@pkmer-music-ui/vue/tabs'
import Password from './Password.vue'
</script>

<template>
  <section class="mx-auto my-5 h-fit w-3/4">
    <PkmerTabs tab-position="left">
      <PkmerTabPanne label="个人资料" name="first">1 </PkmerTabPanne>
      <PkmerTabPanne label="更改密码" name="second">
        <Password />
      </PkmerTabPanne>
      <PkmerTabPanne label="账号和安全" name="third"> 3</PkmerTabPanne>
    </PkmerTabs>
  </section>
</template>
