import { ref, onMounted } from 'vue'
import type { ImageLoadingStatus } from './types'

export function useImageLoadingStatus(src?: string) {
  const imageLoadingStatus = ref<ImageLoadingStatus>('loading')
  const updateStatus = (status: ImageLoadingStatus) => () => {
    imageLoadingStatus.value = status
  }

  onMounted(() => {
    if (!src) {
      updateStatus('error')()
      return
    }
    const image = new window.Image()
    updateStatus('loading')()
    image.onload = updateStatus('loaded')
    image.onerror = updateStatus('error')
    image.src = src
  })

  return imageLoadingStatus
}
