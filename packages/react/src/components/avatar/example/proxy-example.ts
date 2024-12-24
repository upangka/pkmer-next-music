type CallableWithCache = {
  [k in number]: number
} & ((number: number) => number)

const changeNumber = (number: number) => {
  console.log(`处理number = ${number}`)
  return number * 2
}

function factory() {
  const cache = new Map()

  return new Proxy(changeNumber, {
    apply(target, thisArg, argumentsList) {
      return target(argumentsList[0])
    },
    get(_, element) {
      if (typeof element === 'string') {
        const num = +element
        if (!cache.has(element)) {
          console.log('miss cache')
          cache.set(element, changeNumber(num))
        } else {
          console.log('hit cache')
        }
        return cache.get(element)
      } else {
        throw new Error('not type number')
      }
    }
  }) as unknown as CallableWithCache
}

const objProxy = factory()
console.log({
  a: objProxy[1],
  c: objProxy[2],
  d: objProxy[3],
  b: objProxy[1]
})
console.log('------------------------')
console.log(objProxy(10))

/**
miss cache
处理number = 1
miss cache
处理number = 2
miss cache
处理number = 3
hit cache
{ a: 2, c: 4, d: 6, b: 2 }
------------------------
处理number = 10
20
 */
