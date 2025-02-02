<script lang="tsx">
interface Props {
  rating?: number
  maxRating?: number
  activeColor?: string
  inactiveColor?: string
  size?: number
  disabled?: boolean
}
</script>

<script lang="tsx" setup>
import { computed } from 'vue'
import PkmerIcon from '../icon/PkmerIcon.vue'
import type { JSX } from 'vue/jsx-runtime'

const modelValue = defineModel('modelValue', { type: Number })

const props = withDefaults(defineProps<Props>(), {
  rating: 3.5,
  maxRating: 5,
  activeColor: '#ffa500',
  inactiveColor: '#a3a6ad',
  size: 30,
  disabled: false
})

const emit = defineEmits<{
  (e: 'change', id: number): void
}>()

const renderStars = computed<JSX.Element[]>(() => {
  const count = modelValue.value ?? props.rating
  return Array.from({ length: props.maxRating }, (_, i) => {
    if (count >= i + 1) {
      return (
        <PkmerIcon
          icon='material-symbols:star'
          style={{ color: props.activeColor }}
          width={props.size}
          height={props.size}
        />
      )
    } else if (count >= i + 0.5) {
      return (
        <PkmerIcon
          icon='material-symbols:star-half'
          style={{ color: props.activeColor }}
          width={props.size}
          height={props.size}
        />
      )
    } else {
      return (
        <PkmerIcon
          icon='material-symbols:star-outline'
          style={{ color: props.inactiveColor }}
          width={props.size}
          height={props.size}
        />
      )
    }
  })
})

function handleHover(index: number) {
  if (props.disabled) return
  modelValue.value = index + 1
}

function handleClick(index: number) {
  // 因为数组下表是以0开始的，所以+1
  emit('change', index + 1)
}
</script>

<template>
  <div class="star-group">
    <component
      :is="star"
      v-for="(star, index) in renderStars"
      :key="index"
      @mouseover="() => handleHover(index)"
      @click="() => handleClick(index)"
      @mouseleave="() => handleHover(-1)"
    />
  </div>
</template>
