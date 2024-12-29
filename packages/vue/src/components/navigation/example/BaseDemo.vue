<script lang="ts" setup>
import NavigationRoot from "../NavigationRoot.vue"
import NavigationList from "../NavigationList.vue"
import NavigationItem from "../NavigationItem.vue"
import NavigationIndicator from "../NavigationIndicator.vue"
</script>

<template>
  <NavigationRoot>
    <NavigationList :gap="30">
      <NavigationItem>
        <a href="/">首页</a>
      </NavigationItem>
      <NavigationItem>歌单</NavigationItem>
      <NavigationItem>歌手杰</NavigationItem>
    </NavigationList>
    <NavigationIndicator color="black" />
  </NavigationRoot>
</template>

<style lang="scss" scoped></style>
