import { createContext, useContext, useState } from 'react'

interface MenuContextType {
  collapsed: boolean
  changeCollapsed: (status: boolean) => void
}

interface MenuProviderProps {
  children: React.ReactNode
}

const MenuContext = createContext<MenuContextType | undefined>(undefined)

const useMenuContext = () => {
  const context = useContext(MenuContext)
  if (!context) {
    throw new Error('useMenuContext must be used within a MenuProvider')
  }
  return context
}

const MenuProvider: React.FC<MenuProviderProps> = ({ children }) => {
  const [collapsed, setCollapsed] = useState(true)

  function changeCollapsed(status: boolean) {
    setCollapsed(status)
  }
  return (
    <MenuContext.Provider
      value={{
        collapsed,
        changeCollapsed
      }}
    >
      {children}
    </MenuContext.Provider>
  )
}

export { MenuProvider, MenuContext, useMenuContext }
