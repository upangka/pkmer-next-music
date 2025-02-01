<script lang="ts" setup>
import { PkmerRating } from '@pkmer-music/web/components/star'
</script>

<template>
  <PkmerRating :rating="3.5" :max-rating="6" activeColor="red" />
</template>
