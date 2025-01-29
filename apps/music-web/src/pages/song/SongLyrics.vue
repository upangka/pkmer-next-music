<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getSongDetail, type SongDetail } from '@pkmer-music/web/api'
const songDetail = ref<SongDetail>()

// onMounted(async () => {
//   TODO 调用接口
//   const data = await getSongDetail(Number(1).toString())
//   songDetail.value = data
// })
const regex = /^\[(?<time>\d{2}:\d{2}(.\d{3})?)\](?<text>.*)/

type LyricLine = {
  time: string
  text: string
}

const lyrics = reactive<LyricLine[]>([])
onMounted(async () => {
  // load lyric
  const res = await fetch(
    'http://localhost:5173/%E9%BB%84%E6%98%8F%20(%E5%A5%B3%E5%A3%B0%E7%89%88)(DJ%E6%AD%8C%E8%80%85%E8%BE%BE%E8%BE%BE%E7%89%88)%20-%20Kag%20Chuu.lrc'
  )
  const data = await res.text()
  console.log(data)
  data.split('\n').forEach(line => {
    const result = line.match(regex)
    console.log('->', line)
    if (result) {
      const { time, text } = result.groups as unknown as LyricLine
      lyrics.push({ time, text })
    }
  })
})
</script>
<template>
  <section class="lyrics-content__container">
    <ul class="lyrics-content">
      <li :class="['line', index === 6 && 'active']" v-for="(item, index) in lyrics" :key="index">
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
    font-size: 110%;

    .line {
      padding-bottom: 6px;
      opacity: 0.2;
      &.active {
        color: white;
        opacity: 1;
        font-size: 135%;

        & + li {
          font-size: 120%;
        }
      }
    }
  }
}
</style>
