<script lang="ts">
interface Props {
  type?: 'submit' | 'button' | 'reset'
}
</script>
<script setup lang="ts">
const props = withDefaults(defineProps<Props>(), {
  type: 'button'
})
const emit = defineEmits(['click'])
</script>
<template>
  <button :type="type" class="btn-wrapper" @click="emit('click')">
    <span class="btn-name">
      <slot></slot>
    </span>
  </button>
</template>

<style lang="scss" scoped>
$blue: #0367a6;
$lightblue: #008997;

.btn-wrapper {
  background-image: linear-gradient(90deg, $blue 0%, $lightblue 75%);
  color: white;
  border: 1px solid $lightblue;
  padding: 0.9rem 4rem;
  border-radius: 20px;

  .btn-name {
    color: white;
    font-size: 13px;
    white-space: nowrap;
  }
}
</style>
