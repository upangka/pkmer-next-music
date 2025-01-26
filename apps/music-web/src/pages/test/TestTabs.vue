<script setup lang="ts">
import { PkmerTabPanne, PkmerTabs } from '@pkmer-music-ui/vue/tabs'
</script>
<template>
  <section class="test-tabs__container mx-auto w-fit border-2 border-black p-5 shadow-lg">
    <PkmerTabs tab-position="left">
      <PkmerTabPanne label="Tab 1" name="first">
        <div class="demo bg-pink-500">Tab 1 content</div>
      </PkmerTabPanne>
      <PkmerTabPanne label="Tab 2" name="second">
        <div class="demo bg-orange-500">Tab 2 content</div>
      </PkmerTabPanne>
      <PkmerTabPanne label="Tab 3" name="third">
        <div class="demo bg-green-500">Tab 3 content</div>
      </PkmerTabPanne>
      <PkmerTabPanne label="Tab 4" name="fourth">
        <div class="demo bg-blue-500">Tab 4 content</div>
      </PkmerTabPanne>
      <PkmerTabPanne label="Tab 5" name="fifth">
        <div class="demo bg-red-500">Tab 5 content</div>
      </PkmerTabPanne>
    </PkmerTabs>
  </section>
</template>
<style lang="scss" scoped>
.test-tabs__container {
  .demo {
    @apply flex h-[50vh] w-[80vw] items-center justify-center p-7 text-white;
  }
}
</style>
