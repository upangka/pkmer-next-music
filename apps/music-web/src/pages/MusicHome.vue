<script lang="ts" setup>
import {
  MusicHeader,
  MusicFooter,

} from '@components'

import {
  PkmerContainer,
  PkmerMain,
  PkmerFooter,
  PkmerHeader
} from "@pkmer-music-ui/vue/container"

</script>

<template>
  <PkmerContainer class="min-w-[870px]">
    <PkmerHeader>
      <MusicHeader />
    </PkmerHeader>

    <PkmerMain>
      <RouterView />
    </PkmerMain>

    <PkmerFooter>
      <MusicFooter />
    </PkmerFooter>
  </PkmerContainer>
</template>
