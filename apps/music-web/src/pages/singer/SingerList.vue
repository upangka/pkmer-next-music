<script lang="ts" setup>
import { singerStyles } from "@pkmer-music/web/enums"
import { NavigationIndicator, NavigationItem, NavigationList, NavigationRoot } from "@pkmer-music-ui/vue/navigation"
</script>

<template>
  <div class="singer-list__container">
    <NavigationRoot>
      <NavigationList :show-border="false" :item-gap="10">
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

<style lang="scss" scoped>
@use "@pkmer-music/web/assets/styles/mixins.scss" as PkmerMixin;

.singer-list__container {
  @include PkmerMixin.play-list__container();
  height: 100%;
}
</style>
