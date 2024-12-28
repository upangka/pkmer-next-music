<script lang="ts" setup>
import { storeToRefs } from "pinia"
import { useMusicPannelStore } from "@pkmer-music/web/stores";

const musicPannelStore = useMusicPannelStore()
const { showAssider, currentPlayingSongId } = storeToRefs(musicPannelStore)
const { songs } = musicPannelStore
</script>

<template>
  <Transition name="fade">
    <section v-if="showAssider" class="assider-container">
      <h1 class="title">当前播放</h1>
      <p class="total">共{{ songs.length }}首</p>
      <ul class="songs-list gap-2">
        <!-- <li class="playing">月亮里的阿妹 (DJ村长版|DJ版)
        </li> -->
        <li v-for="song in songs" :key="song.id" :class="[
          currentPlayingSongId === song.id && 'playing'
        ]" @click="() => musicPannelStore.play(song.id)">{{ song.name }}</li>
      </ul>
    </section>
  </Transition>

</template>

<style lang="scss" scoped>
@import "@pkmer-music/web/assets/styles/__variables.scss";

.fade-enter-active,
.fade-leave-active {
  transition: transform 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  transform: translateX(100%)
}

.assider-container {
  position: fixed;
  padding: 4px 12px;
  top: $header-height;
  bottom: 0;
  right: 0;
  background-color: white;
  width: 300px;
  z-index: $music-assider-z-index;


  .title {
    text-align: center;
    font-size: 18px;
    line-height: 26px;
  }

  .total {
    text-align: center;
    font-size: 13px;
    line-height: 21px;
  }

  .songs-list {
    padding: 10px 0;
    display: flex;
    flex-direction: column;
    list-style: decimal-leading-zero;
    list-style-position: inside;

    li {
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      font-size: 16px;
      line-height: 24px;
      cursor: pointer;

      &.playing {
        color: #15803d;
      }
    }

  }
}
</style>
