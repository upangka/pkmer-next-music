import { ThemeProvider } from './themeContext'
import { MenuProvider } from './menuContext'
import { BreadCrumbProvider } from './breadCrumbContext'

interface AppProvidersProps {
  children: React.ReactNode
}

const AppProviders: React.FC<AppProvidersProps> = ({ children }) => {
  return (
    <>
      <ThemeProvider>
        <MenuProvider>
          <BreadCrumbProvider>{children}</BreadCrumbProvider>
        </MenuProvider>
      </ThemeProvider>
    </>
  )
}

export default AppProviders
