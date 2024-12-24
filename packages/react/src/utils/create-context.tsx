import React from 'react'

/**
 * 创建context值的类型，
 * 因为封装了一个Provider组件，需要传递children
 */
type ProviderPropsWithChildren<T> = T & {
  children: React.ReactNode
}

/**
 * 创建context命名空间
 * @param scopeName 命名空间
 * @returns
 */
export function createContextScope(scopeName: string) {
  scopeName = scopeName.slice(0, 1).toLocaleLowerCase() + scopeName.slice(1, scopeName.length)

  /**
   * 创建react context
   * @return 返回一个组件，用于封装context
   */
  function createContext<ContextValueType>(defaultValue?: ContextValueType) {
    /**
     * 使用React创建context
     */
    const Context = React.createContext<ContextValueType | undefined>(defaultValue)
    Context.displayName = `${scopeName}-Context`

    /**
     * 创建一个组件用于封装context
     */
    const Provider: React.FC<ProviderPropsWithChildren<ContextValueType>> = props => {
      const { children, ...context } = props
      const value = React.useMemo(() => context, Object.values(context)) as ContextValueType
      return <Context.Provider value={value}>{children}</Context.Provider>
      // return <Context.Provider value={value} {...props} />
    }

    function useContext() {
      const context = React.useContext(Context)
      if (context === undefined) {
        throw new Error(`use${scopeName}Context must be used within a ${scopeName}Provider`)
      }
      return context
    }

    Provider.displayName = `${scopeName}Provider`
    useContext.displayName = `use${scopeName}Context`

    return [Provider, useContext] as const
  }

  createContext.displayName = `${scopeName}-createContext`
  return createContext
}
