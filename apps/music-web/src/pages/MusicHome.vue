<script lang="ts" setup>
import { MusicHeader, MusicFooter, PlayAssider, PlayBar } from '@pkmer-music/web/components'

import { PkmerContainer, PkmerMain, PkmerFooter, PkmerHeader } from '@pkmer-music-ui/vue/container'
</script>

<template>
  <PkmerContainer class="min-w-[870px]">
    <PkmerHeader>
      <MusicHeader />
    </PkmerHeader>

    <PkmerMain class="main">
      <RouterView />
      <PlayAssider />
      <PlayBar />
    </PkmerMain>

    <PkmerFooter>
      <MusicFooter />
    </PkmerFooter>
  </PkmerContainer>
</template>
<style lang="scss" scoped>
@import "@pkmer-music/web/assets/styles/__variables.scss";

.main {
  margin-top: $header-height;
}
</style>
