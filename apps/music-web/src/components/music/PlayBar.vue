<script lang="ts" setup>
import { storeToRefs } from 'pinia'
import { ref, reactive, watch, computed } from 'vue'
import { useRouter } from 'vue-router'
import { PkmerIcon } from '@pkmer-music-ui/vue/icon'
import { formatTime } from '@pkmer-music/web/utils'
import { useMusicPannelStore } from '@pkmer-music/web/stores'
import { collectSong, cancelCollectSong } from '@pkmer-music/web/api'
const router = useRouter()
const musicPannelStore = useMusicPannelStore()
const { audioRef, currentPlayingSongId, playBayStatus } = storeToRefs(musicPannelStore)
const { songs } = musicPannelStore

const status = reactive({
  showPlayBar: true,
  currentIndex: musicPannelStore.currentPlayingIndex
})

// const audioRef = ref<HTMLAudioElement | null>(null)
const progressContainerRef = ref<HTMLDivElement | null>(null)
const progressWidth = ref('0%')
const iconBtnSize = 30

/**
 * 监听当前播放歌曲的index
 */
watch(
  () => musicPannelStore.currentPlayingIndex,
  index => {
    status.currentIndex = index
  }
)

watch(
  () => playBayStatus.value.isPlaying,
  isCurrentPlaying => {
    if (isCurrentPlaying) {
      audioRef.value?.play()
    } else {
      audioRef.value?.pause()
    }
  }
)

/**
 * currentIndex变化的时候，会更新当前的歌曲
 * 然后audio会变化，此时我们在播放音乐。
 */
watch(
  () => audioRef.value,
  audio => {
    if (audio) {
      console.log(audio)
      // 更新store中的当前播放歌曲id
      musicPannelStore.$patch(state => {
        state.currentPlayingSongId = songs[status.currentIndex].id
      })

      if (playBayStatus.value.isPlaying) {
        audio.play()
      }
    }
  },
  { immediate: true }
)

const progressContainerWidth = computed(() => {
  if (progressContainerRef.value) {
    return progressContainerRef.value.clientWidth
  }
  return 0
})

const currentSong = computed(() => {
  return songs[status.currentIndex]
})

function togglePlaying(_isPlaying: boolean, isShowMusicInfo: boolean) {
  playBayStatus.value.isPlaying = _isPlaying
  playBayStatus.value.showMusicInfo = isShowMusicInfo
}

/**
 * 处理显示和隐藏音乐面板
 */
function handleToggle() {
  if (status.showPlayBar) {
    // 处理关闭
    playBayStatus.value.showMusicInfo = false
    window.setTimeout(() => {
      status.showPlayBar = false
    }, 300)
  } else {
    // 处理展开
    if (playBayStatus.value.isPlaying) {
      status.showPlayBar = true
      window.setTimeout(() => {
        playBayStatus.value.showMusicInfo = true
      }, 300)
    } else {
      status.showPlayBar = true
    }
  }
}

/**
 * 更新音乐播放进度
 */
function updateProgress(e: Event) {
  const srcElement = e.target as HTMLMediaElement
  const { currentTime, duration: totalTime } = srcElement
  const width = `${(currentTime / totalTime) * 100}%`
  progressWidth.value = width
  // console.log(`currentTime = ${currentTime}, totalTime = ${totalTime} 播放进度:${currentTime / totalTime * 100}%`)
  // if ("100%" === width) {
  //   togglePlaying(false, false)
  //   progressWidth.value = '0%'
  // }
}

/**
 * 跳到指定时间位置
 */
function jumpToTime(e: PointerEvent) {
  const result = e.offsetX / progressContainerWidth.value
  // console.log(`width: ${result * 100}%`)
  if (audioRef.value) {
    audioRef.value.currentTime = audioRef.value.duration * result
  }
}

function nextSong() {
  status.currentIndex = (status.currentIndex + 1) % songs.length
}

function prevSong() {
  status.currentIndex = (status.currentIndex - 1 + songs.length) % songs.length
}

/**
 * 隐藏和现实asside
 */
function toggleMusicAsside() {
  musicPannelStore.$patch(state => {
    state.showAssider = !state.showAssider
  })
}

function routeToSongDetail() {
  router.push(`/lyrics/${currentPlayingSongId.value}`)
}

function handleCancelCollect() {
  musicPannelStore.cancelCollectCurrentSong(id => {
    cancelCollectSong(id)
  })
}

function handleCollectSong() {
  musicPannelStore.collectCurrentSong(id => {
    collectSong(id)
  })
}
</script>
<template>
  <section class="play-bar__container">
    <!-- 音乐源start -->
    <!-- 这里用key来表明这是一个新的元素，和react渲染树差不多 -->
    <audio
      :key="currentSong.name"
      @ended="nextSong"
      @timeupdate="updateProgress"
      :src="currentSong.link"
      ref="audioRef"
    ></audio>
    <!-- 音乐源end -->
    <!-- 显示隐藏按钮start -->
    <button class="icon-btn" @click="handleToggle">
      <PkmerIcon
        v-if="status.showPlayBar"
        icon="icon-park-outline:down-c"
        style="color: #000"
        :width="iconBtnSize"
        :height="iconBtnSize"
      />
      <PkmerIcon
        v-else
        icon="icon-park-outline:up-c"
        style="color: #000"
        :width="iconBtnSize"
        :height="iconBtnSize"
      />
    </button>
    <!-- 显示隐藏按钮end -->

    <!-- 进度条start -->
    <Transition name="expand">
      <section class="music-container" v-if="status.showPlayBar">
        <!-- 音乐进度播放start -->
        <div
          :class="[
            'music-info',
            playBayStatus.showMusicInfo && playBayStatus.isPlaying && 'playing'
          ]"
        >
          <h1 class="music-title">{{ currentSong.name }}</h1>
          <div
            ref="progressContainerRef"
            class="progress-container"
            @pointerdown.prevent="jumpToTime"
          >
            <div
              :style="{
                width: progressWidth
              }"
              class="progress"
            ></div>
            <span class="current-time">
              {{ formatTime(audioRef?.currentTime || 0) }} /
              {{ formatTime(audioRef?.duration || 0) }}</span
            >
          </div>
        </div>

        <!-- 音乐进度播放end -->
        <!-- 音乐控制面板start -->
        <div :class="['music-control__container']">
          <div :class="['img-wrapper', playBayStatus.isPlaying && 'playing']">
            <img :src="currentSong.picture" :alt="currentSong.name" />
          </div>

          <ul class="navigation">
            <li>
              <button @click="prevSong">
                <PkmerIcon icon="tabler:player-track-prev-filled" />
              </button>
            </li>
            <li>
              <button v-if="playBayStatus.showMusicInfo" @click="() => togglePlaying(false, false)">
                <PkmerIcon icon="gridicons:pause" :width="40" :height="40" />
              </button>
              <button v-else @click="() => togglePlaying(true, true)">
                <PkmerIcon icon="icon-park-solid:play" :width="40" :height="40" />
              </button>
            </li>
            <li>
              <button @click="nextSong">
                <PkmerIcon icon="tabler:player-track-next-filled" />
              </button>
            </li>
          </ul>

          <ul class="tools">
            <li class="translate-y-[2px]">
              <button @click="routeToSongDetail">
                <PkmerIcon icon="material-symbols-light:lyrics-outline" />
              </button>
            </li>
            <li>
              <button v-if="musicPannelStore.getIsCollectCurrentSong" @click="handleCancelCollect">
                <PkmerIcon :style="{ color: 'red' }" icon="weui:like-filled" />
              </button>
              <button v-else @click="handleCollectSong">
                <PkmerIcon icon="weui:like-outlined" />
              </button>
            </li>
            <li>
              <button>
                <PkmerIcon icon="mdi-light:cloud-upload" />
              </button>
            </li>
            <li>
              <button @click="toggleMusicAsside">
                <PkmerIcon icon="material-symbols-light:menu-rounded" />
              </button>
            </li>
          </ul>
        </div>
        <!-- 音乐控制面板end -->
      </section>
    </Transition>
    <!-- 进度条end -->
  </section>
</template>

<style lang="scss" scoped>
/* @import "@pkmer-music/web/assets/styles/__variables.scss"; */
@use '@pkmer-music/web/assets/styles/__variables.scss' as pkmerVars;
$bar-height: 55px;

.play-bar__container {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  color: black;
  z-index: pkmerVars.$music-panel-z-index;
  min-width: 1460px;

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
      padding: 0 20px 10px 30px;
      opacity: 0;
      z-index: -1;
      width: 80%;
      left: 12%;
      background-color: #e5e7eb;
      border-top-right-radius: 20px;
      border-top-left-radius: 20px;
      transition:
        transform 0.3s ease,
        opacity 0.3s ease;

      &.playing {
        opacity: 1;
        z-index: pkmerVars.$music-info-show-z-index;
        transform: translateY(-100%);
      }

      .music-title {
        font-size: 18px;
        line-height: 26px;
        padding: 10px 35px;
        color: green;
      }

      .progress-container {
        position: relative;
        width: 100%;
        height: 10px;
        background-color: white;
        border: 1px solid gray;
        border-radius: 10px;

        .progress {
          height: 100%;
          background-color: green;
          transition: width 0.1s linear;
        }

        .current-time {
          position: absolute;
          left: 2%;
          padding-top: 5px;
          font-style: italic;
          z-index: pkmerVars.$music-time-z-indedx;
        }
      }
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
        z-index: pkmerVars.$music-image-z-index;
        animation: rotate 10s linear infinite;
        animation-play-state: paused;

        @keyframes rotate {
        }
      }
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
        z-index: pkmerVars.$music-image-z-index;
        animation: rotate 10s linear infinite;
        animation-play-state: paused;

        @keyframes rotate {
          from {
            transform: rotate(0deg);
          }

          to {
            transform: rotate(360deg);
          }
        }

        &.playing {
          animation-play-state: running;
        }

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

.expand-enter-from,
.expand-leave-to {
  max-height: 0;
}

.expand-enter-to,
.expand-leave-from {
  max-height: $bar-height;
}

.expand-enter-active,
.expand-leave-active {
  transition: max-height 0.3s ease;
}
</style>
