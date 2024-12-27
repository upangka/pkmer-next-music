<script lang="ts" setup>

interface Props {
  width?: number,
  height?: number,
  placeholder: string,
  icon: string,
  type?: string,
  require?: boolean
}

const props = withDefaults(defineProps<Props>(), {
  width: 20,
  height: 20,
  type: "text",
  require: true
})


</script>

<template>
  <div class="mb-[30px] flex justify-center items-center px-5">
    <label class="relative w-full flex justify-center items-center">
      <iconify-icon :width="props.width" :height="props.height" :icon="props.icon"
        class="absolute left-1 top-1/2 -translate-y-1/2"></iconify-icon>
      <input :type="props.type" class="input" required />
      <span class="absolute left-[30px] top-1/2 -translate-y-1/2 text-black">
        {{ props.placeholder }}</span>
    </label>
  </div>
</template>

<style lang="scss" scoped>
.input {
  padding: 4px 8px 6px 0px;
  width: 80%;
  background: transparent !important;
  appearance: none;
  border-bottom: 1px solid black;
  outline: none;

  /* https://stackoverflow.com/questions/2781549/removing-input-background-colour-for-chrome-autocomplete */
  /* 针对 autofill 的处理 */
  &:-webkit-autofill {
    // 5000s大约83分钟，让用户无法察觉背景颜色的变化
    transition: background-color 5000s ease-in-out 0s;
    box-shadow: 0 0 0 1000px transparent inset !important;
    /* 强制覆盖灰色背景 */
    -webkit-text-fill-color: black !important;
    /* 确保字体颜色一致 */
  }


  &:focus~span,
  &:valid~span {
    top: -5px;
    color: black;
    font-size: 12px;
  }

  &~span {
    transition: all 0.3s ease-in-out;
  }
}
</style>
