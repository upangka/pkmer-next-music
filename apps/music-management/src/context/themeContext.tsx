import { createContext, useContext, useEffect, useState, use } from 'react'
import { getTheme } from '@pkmer-music/management/actions'
type ThemeType = 'light' | 'dark'

interface ThemeContextType {
  theme: ThemeType
  toggleTheme: () => void
}

interface ThemeProviderProps {
  children: React.ReactNode
}

const ThemeContext = createContext<ThemeContextType | undefined>(undefined)

const useThemeContext = () => {
  const context = useContext(ThemeContext)
  if (!context) {
    throw new Error('useThemeContext must be used within a ThemeProvider')
  }
  return context
}

const ThemeProvider: React.FC<ThemeProviderProps> = ({ children }) => {
  const [theme, setTheme] = useState<ThemeType>('light')

  // useEffect(() => {
  //   // api
  //   function handleThemeChange() {
  //     getTheme().then(theme => {
  //       setTheme(theme)
  //       console.log('theme provider执行', theme)
  //     })
  //   }
  //   handleThemeChange()
  // })

  function toggleTheme() {
    setTheme(prevTheme => (prevTheme === 'light' ? 'dark' : 'light'))
  }
  return (
    <ThemeContext.Provider
      value={{
        theme,
        toggleTheme
      }}
    >
      {children}
    </ThemeContext.Provider>
  )
}

export { ThemeProvider, ThemeContext, useThemeContext }
