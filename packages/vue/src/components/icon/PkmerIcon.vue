<script lang="ts" setup>
interface IconProps {
  width?: number
  height?: number
  icon: string
}
const props = withDefaults(defineProps<IconProps>(), {
  width: 24,
  height: 24
})
</script>

<template>
  <iconify-icon :icon="props.icon" :width="props.width" :height="props.height"></iconify-icon>
</template>

<style lang="scss" scoped></style>
