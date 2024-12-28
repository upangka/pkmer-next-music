/**
 * 格式化时间
 * 输入 32.186883
 * 格式化为：00:32
 * @param seconds
 * @returns
 */
export function formatTime(seconds: number) {
  const minutes = Math.floor(seconds / 60)
  const remainingSeconds = seconds % 60

  const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes
  const formattedSeconds = remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds

  return `${formattedMinutes}:${formattedSeconds}`.split('.')[0]
}
