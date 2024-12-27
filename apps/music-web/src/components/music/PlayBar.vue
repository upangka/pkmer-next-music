<script lang="ts" setup>
import { ref } from "vue";
import { PkmerIcon } from "@pkmer-music-ui/vue/icon"
const showPlayBar = ref(false);
const iconBtnSize = 30;
</script>

<template>
  <section class="play-bar__container">
    <!-- 显示隐藏按钮start -->
    <button class="icon-btn" @click="showPlayBar = !showPlayBar">
      <PkmerIcon v-if="showPlayBar" icon="icon-park-outline:down-c" style="color: #000" :width="iconBtnSize"
        :height="iconBtnSize" />
      <PkmerIcon v-else icon="icon-park-outline:up-c" style="color: #000" :width="iconBtnSize" :height="iconBtnSize" />
    </button>
    <!-- 显示隐藏按钮end -->

    <!-- 进度条start -->
    <Transition name="fade">
      <section class="play-bar__content" v-if="showPlayBar">
        see me
      </section>
    </Transition>

    <!-- 进度条end -->

  </section>
</template>

<style lang="scss" scoped>
$bar-height: 100px;

.play-bar__container {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  color: white;


  .icon-btn {
    position: relative;
    top: -20px;
    left: 3%;
    padding: 2px 5px;
  }

  .play-bar__content {
    height: $bar-height;
    background-color: black;
  }
}


.fade-enter-from,
.fade-leave-to {
  max-height: 0;
}

.fade-enter-to,
.fade-leave-from {
  max-height: $bar-height;
}

.fade-enter-active,
.fade-leave-active {
  transition: max-height 0.3s ease;
}
</style>
