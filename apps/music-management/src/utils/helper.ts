// function noop(): void
// function noop<T>(arg?: T): void
// function noop<T = any>(_?: T): void {}

export const noop = <T>(_arg?: T): void => {}
