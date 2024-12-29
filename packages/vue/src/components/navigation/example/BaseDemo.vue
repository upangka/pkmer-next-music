<script lang="ts" setup>
import NavigationRoot from "../NavigationRoot.vue"
import NavigationList from "../NavigationList.vue"
import NavigationItem from "../NavigationItem.vue"
import NavigationIndicator from "../NavigationIndicator.vue"
</script>

<template>
  <NavigationRoot>
    <NavigationList>
      <NavigationItem>Home</NavigationItem>
      <NavigationItem>Profile</NavigationItem>
      <NavigationItem>Messages</NavigationItem>
      <NavigationIndicator />
    </NavigationList>
  </NavigationRoot>
</template>

<style lang="scss" scoped></style>
