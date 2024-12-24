import { useState, useEffect } from 'react'
export type ImageLoadingStatus = 'loading' | 'loaded' | 'error' | 'idle'

export function useImageLoadingStatus(src?: string) {
  const [status, setStatus] = useState<ImageLoadingStatus>('loading')

  useEffect(() => {
    if (!src) {
      setStatus('error')
      return
    }
    let isMounted = true

    const updateStatus = (status: ImageLoadingStatus) => () => {
      if (isMounted) setStatus(status)
    }

    const image = new window.Image()
    updateStatus('loading')()
    image.onload = updateStatus('loaded')
    image.onerror = updateStatus('error')
    image.src = src

    return () => {
      isMounted = false
    }
  }, [src])

  return status
}
