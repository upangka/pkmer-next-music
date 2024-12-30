<script lang="ts" setup>
import { singerStyles } from "@pkmer-music/web/enums"
import { NavigationIndicator, NavigationItem, NavigationList, NavigationRoot } from "@pkmer-music-ui/vue/navigation"
</script>

<template>
  <div>
    <NavigationRoot>
      <NavigationList>
        <template v-for="singerStyle in singerStyles" :key="singerStyle.type">
          <NavigationItem>
            {{ singerStyle.name }}
          </NavigationItem>
        </template>
      </NavigationList>
      <NavigationIndicator />
    </NavigationRoot>
  </div>
</template>

<style lang="scss" scoped></style>
