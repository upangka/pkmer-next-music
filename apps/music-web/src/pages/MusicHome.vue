<script lang="ts" setup>
import { MusicHeader, MusicFooter, MusicPlayPannel } from '@pkmer-music/web/components'

import { PkmerContainer, PkmerMain, PkmerFooter, PkmerHeader } from '@pkmer-music-ui/vue/container'
</script>

<template>
  <PkmerContainer>
    <PkmerHeader>
      <MusicHeader />
    </PkmerHeader>

    <PkmerMain class="main">
      <RouterView />
      <MusicPlayPannel />
    </PkmerMain>

    <PkmerFooter>
      <MusicFooter />
    </PkmerFooter>
  </PkmerContainer>
</template>
<style lang="scss" scoped>
@use "@pkmer-music/web/assets/styles/__variables.scss" as PkmerVars;

.main {
  margin-top: PkmerVars.$header-height;
}
</style>
