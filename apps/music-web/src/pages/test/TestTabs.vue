<script setup lang="ts">
import { PkmerTabPanne, PkmerTabs } from '@pkmer-music-ui/vue/tabs'
</script>
<template>
  <PkmerTabs tab-position="left">
    <PkmerTabPanne label="Tab 1" name="first">
      <div>Tab 1 content</div>
    </PkmerTabPanne>
    <PkmerTabPanne label="Tab 2" name="second">
      <div>Tab 2 content</div>
    </PkmerTabPanne>
  </PkmerTabs>
</template>
