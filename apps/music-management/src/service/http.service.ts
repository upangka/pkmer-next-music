import { createHttpInstance } from '@pkmer/libs/http'

const getHttpInstance = createHttpInstance(process.env.API_URL)

export const httpService = getHttpInstance()
