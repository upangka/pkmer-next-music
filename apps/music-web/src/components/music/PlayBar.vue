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
      <section class="music-container" v-if="showPlayBar">
        <!-- 音乐进度播放start -->
        <div class="music-info">
          <h1 class="music-title">黄昏DJ</h1>
          <div class="progress-container">
            <div class="progresss"></div>
          </div>
        </div>

        <!-- 音乐进度播放end -->
        <div class="music-control__container">
          <div class="img-wrapper">
            <img src="https://i.pravatar.cc/300" alt="">
          </div>

          <ul class="navigation">
            <li>
              <button>
                <PkmerIcon icon="tabler:player-track-prev-filled" />
              </button>
            </li>
            <li>
              <button>
                <PkmerIcon icon="icon-park-solid:play" :width="40" :height="40" />
              </button>
            </li>
            <li>
              <button>
                <PkmerIcon icon="tabler:player-track-next-filled" />
              </button>
            </li>
          </ul>

          <ul class="tools">
            <li>
              <button>
                <PkmerIcon icon="weui:like-outlined" />
              </button>
            </li>
            <li>
              <button>
                <PkmerIcon icon="mdi-light:cloud-upload" />
              </button>
            </li>
            <li>
              <button>
                <PkmerIcon icon="material-symbols-light:menu-rounded" />
              </button>
            </li>
          </ul>
        </div>

      </section>
    </Transition>

    <!-- 进度条end -->

  </section>
</template>

<style lang="scss" scoped>
$bar-height: 55px;

.play-bar__container {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  color: black;


  .icon-btn {
    position: relative;
    top: -20px;
    left: 3%;
    padding: 2px 5px;
  }

  .music-container {
    position: relative;
    height: $bar-height;
    background-color: #e5e7eb;

    .music-info {
      position: absolute;
      opacity: 0;
    }

    .music-control__container {
      display: grid;
      grid-template-columns: repeat(3, 1fr);

      .img-wrapper {
        position: relative;
        width: 110px;
        height: 110px;
        border-radius: 100%;
        overflow: hidden;
        left: 20%;
        top: -50%;

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }


      .share-layout {
        height: $bar-height;
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 30px;
      }

      .navigation {
        @extend .share-layout;
        grid-column: 2;
      }

      .tools {
        @extend .share-layout;
        grid-column: 3;
      }
    }
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
