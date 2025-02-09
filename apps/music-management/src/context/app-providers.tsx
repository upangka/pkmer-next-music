import { ThemeProvider } from './themeContext'
import { MenuProvider } from './menuContext'

interface AppProvidersProps {
  children: React.ReactNode
}

const AppProviders: React.FC<AppProvidersProps> = ({ children }) => {
  return (
    <>
      <ThemeProvider>
        <MenuProvider>{children}</MenuProvider>
      </ThemeProvider>
    </>
  )
}

export default AppProviders
