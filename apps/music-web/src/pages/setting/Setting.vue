<script setup lang="ts">
import {
  PkmerNavigationIndicator,
  PkmerNavigationItem,
  PkmerNavigationList,
  PkmerNavigationRoot
} from '@pkmer-music-ui/vue'
</script>

<template>
  <section class="mx-auto my-4 w-1/2">
    <PkmerNavigationRoot>
      <PkmerNavigationList :item-gap="60" direction="vertical">
        <PkmerNavigationItem>个人资料</PkmerNavigationItem>
        <PkmerNavigationItem> 更改密码 </PkmerNavigationItem>
        <PkmerNavigationItem> 账号安全 </PkmerNavigationItem>
      </PkmerNavigationList>
      <!-- <PkmerNavigationIndicator color="black" /> -->
    </PkmerNavigationRoot>
  </section>
</template>
