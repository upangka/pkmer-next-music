<script setup lang="ts">
import { ref, reactive, watch, onMounted, onUnmounted } from 'vue'
import { storeToRefs } from 'pinia'
import { getSongDetail, type SongDetail } from '@pkmer-music/web/api'
import { useMusicPannelStore } from '@pkmer-music/web/stores'

const { audioRef } = storeToRefs(useMusicPannelStore())

const songDetail = ref<SongDetail>()
const currentLine = ref(0)
const regex = /^\[(?<time>\d{2}:\d{2}(.\d{3})?)\](?<text>.*)/
const lisRef = ref<HTMLLIElement[]>([])

type LyricLine = {
  time: string
  text: string
}

const lyrics = reactive<LyricLine[]>([])
watch(
  () => audioRef.value,
  () => {
    audioRef.value?.addEventListener('timeupdate', handleTimeUpdate)
    // 清空
    lisRef.value = []
  }
)

watch(
  () => currentLine.value,
  newLine => {
    lisRef.value[newLine].scrollIntoView({ behavior: 'smooth', block: 'center' })
  }
)

onMounted(() => {
  if (audioRef.value) {
    audioRef.value.addEventListener('timeupdate', handleTimeUpdate)
  }
})

onMounted(async () => {
  // load lyric
  const res = await fetch(
    'http://localhost:5173/%E9%BB%84%E6%98%8F%20(%E5%A5%B3%E5%A3%B0%E7%89%88)(DJ%E6%AD%8C%E8%80%85%E8%BE%BE%E8%BE%BE%E7%89%88)%20-%20Kag%20Chuu.lrc'
  )
  const data = await res.text()
  console.log(data)
  data.split('\n').forEach(line => {
    const result = line.match(regex)
    if (result) {
      const { time, text } = result.groups as unknown as LyricLine
      lyrics.push({ time, text })
    }
  })
})

onUnmounted(() => {
  if (audioRef.value) {
    audioRef.value.removeEventListener('timeupdate', handleTimeUpdate)
  }
})

function handleTimeUpdate() {
  if (audioRef.value) {
    const currentTime = audioRef.value.currentTime
    let targetLineIndex = 0
    for (let i = 0; i < lyrics.length; i++) {
      const time = parseTime(lyrics[i].time)
      if (currentTime >= time) {
        targetLineIndex = i
      } else {
        currentLine.value = targetLineIndex
        return
      }
    }
  }
}

function parseTime(time: string) {
  const [min, sec] = time.split(':')
  return Number(min) * 60 + Number(sec)
}

function collectLiElement(index: number, el: unknown) {
  if (el instanceof HTMLLIElement) {
    lisRef.value[index] = el
  }
}

// onMounted(async () => {
//   TODO 调用接口
//   const data = await getSongDetail(Number(1).toString())
//   songDetail.value = data
// })
</script>
<template>
  <section class="lyrics-content__container">
    <ul class="lyrics-content">
      <li
        :class="['line', currentLine === index && 'active']"
        v-for="(item, index) in lyrics"
        :key="index"
        :ref="el => collectLiElement(index, el)"
      >
        {{ item.text }}
      </li>
    </ul>
    {{ lyrics.length }}
  </section>
</template>
<style lang="scss" scoped>
.lyrics-content__container {
  border: 2px solid black;
  background-image: linear-gradient(315deg, #7f5a83 0%, #0d324d 74%);
  .lyrics-content {
    height: 60vh;
    width: 100%;
    overflow-y: scroll;
    text-align: center;
    color: white;
    font-size: 105%;

    .line {
      padding-bottom: 6px;
      opacity: 0.2;
      &.active {
        color: rgb(110, 227, 110);
        opacity: 1;
        font-size: 135%;

        & + li {
          font-size: 115%;
          opacity: 0.7;
        }
      }
    }
  }
}
</style>
