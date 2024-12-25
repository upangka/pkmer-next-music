export type GenericFuncType<T> = {
  (...args: T[]): void
}

// TODO：仿照lodash完善一下
export function debounce<T>(fn: GenericFuncType<T>, wait: number) {
  let timer: number
  return callFn
  function callFn(...args: T[]) {
    if (timer) {
      clearTimeout(timer)
    }
    timer = window.setTimeout(() => fn(...args), wait)
  }
}
