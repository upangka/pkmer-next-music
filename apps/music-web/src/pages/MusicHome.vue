<script lang="ts" setup>
import {
  MusicHeader,
  MusicFooter,
  PkmerContainer,
  PkmerMain,
  PkmerFooter,
  PkmerHeader,
} from '@components'
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
