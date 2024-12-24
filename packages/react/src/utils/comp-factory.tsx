import React from 'react'

type CompProps<T extends React.ElementType = keyof React.JSX.IntrinsicElements> =
  React.ComponentPropsWithRef<T>

const createComp = (Component: React.ElementType) => {
  const Comp = React.memo((props: CompProps) => {
    const { ref, children, ...rest } = props
    return React.createElement(Component, { ref, ...rest }, children)
    // return (
    //   <Component {...rest} ref={ref}>
    //     {children}
    //   </Component>
    // );
  })

  return Comp
}

type PkmerRefComponent = {
  [k in keyof React.JSX.IntrinsicElements]: React.ComponentType<React.ComponentPropsWithRef<k>>
}

const jsxFactory = () => {
  const cache = new Map()

  return new Proxy(createComp, {
    apply(target, _thisArg, argumentsList) {
      target(argumentsList[0])
    },
    get(_, element) {
      const el = element as React.ElementType
      if (!cache.has(el)) {
        cache.set(el, createComp(el))
      }
      return cache.get(el)
    }
  }) as unknown as PkmerRefComponent
}

export const pkmer = jsxFactory()
