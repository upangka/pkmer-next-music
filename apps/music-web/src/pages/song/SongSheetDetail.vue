<script lang="ts">
import type { SongListDetail, UserSongListRating } from '@pkmer-music/web/types'
</script>
<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { getSongListDetail, getUserSonglistScore, userAddScore } from '@pkmer-music/web/api'
import { PkmerColumn, PkmerTable, PkmerRating } from '@pkmer-music-ui/vue'

const props = defineProps({
  id: String
})

const songList = ref<SongListDetail>()!
const userSongListScore = ref<UserSongListRating>()
// 评分倍数计算
const SCORE_DOUBLE = 2

onMounted(async () => {
  if (props.id) {
    const data = await getSongListDetail(props.id)
    songList.value = data
  }
})

onMounted(async () => {
  if (props.id) {
    const data = await getUserSonglistScore(props.id)
    if (data) {
      userSongListScore.value = data
      userScore.value = +(data.score / SCORE_DOUBLE).toFixed(1)
      disabled.value = true
    }
  }
})

const score = computed(() => {
  if (songList.value && songList.value.score) {
    return +(songList.value.score / SCORE_DOUBLE).toFixed(1)
  }
  return 0
})

const userScore = ref(0)

const disabled = ref(false)

async function handleUserScoreSubmit(_score: number) {
  try {
    disabled.value = true
    await userAddScore({
      score: _score * SCORE_DOUBLE,
      songListId: props.id!
    })
  } catch (error: any) {
    disabled.value = false
  }
}
</script>
<template>
  <section class="flex gap-5 p-5">
    <!-- left start -->
    <section class="flex w-fit flex-col items-center justify-center gap-9 px-5 py-10 shadow-md">
      <div class="h-[200px] w-[200px] overflow-hidden rounded-md shadow-xl">
        <img class="object-cover" :src="songList?.pic" alt="歌单封面" />
      </div>
      <p v-html="songList?.title" class="font-bold text-gray-500"></p>
    </section>
    <!-- left end -->
    <!-- right start -->
    <section class="flex flex-1 flex-col gap-5 p-8 shadow-lg">
      <!-- 介绍start -->
      <div>
        <header class="py-6 text-lg font-bold text-black">歌单介绍</header>
        <p v-html="songList?.introduction" class="text-gray-500"></p>
      </div>
      <!-- 介绍end -->

      <!-- 评分start -->
      <section class="flex items-center justify-start gap-5">
        <div class="relative pr-[100px]">
          <p class="mb-3 font-bold text-gray-500">歌单评分</p>
          <PkmerRating :rating="score" disabled />
          <span class="absolute bottom-0 right-0 text-6xl font-bold text-black"> {{ score }}</span>
        </div>
        <div>
          <p v-if="disabled" class="mb-3 font-bold text-gray-500">
            已评价<span class="px-2 text-lg text-black">{{ userScore }}</span>
          </p>
          <p v-else>您未评价</p>
          <PkmerRating v-model:model-value="userScore" @change="handleUserScoreSubmit" :disabled />
        </div>
      </section>
      <!-- 评分end -->

      <!-- 表格start -->
      <PkmerTable :data="songList?.songs || []">
        <PkmerColumn label="歌曲" prop="name" align="center" />
        <PkmerColumn label="歌手" prop="singerName" align="center" />
        <PkmerColumn label="专辑" prop="introduction" align="center" />
      </PkmerTable>
      <!-- 表格end -->
    </section>

    <!-- right end -->
  </section>
</template>
