import 'next'
declare global {
  namespace NodeJS {
    interface ProcessEnv {
      SLOGAN: string
      JWT_SECRET: string
      API_URL: string
    }
  }
}
