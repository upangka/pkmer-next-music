<script lang="ts" setup>
interface IconProps {
  width?: number,
  height?: number,
  icon: string
}
const { width, height, icon } = withDefaults(defineProps<IconProps>(), {
  width: 24,
  height: 24
})
</script>

<template>
  <iconify-icon :icon :width :height></iconify-icon>
</template>

<style lang="scss" scoped></style>
