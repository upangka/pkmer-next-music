'use server'
import httpService from '@pkmer-music/management/service'
import { AppResponse } from '@pkmer/libs/http'

export async function deleteSinger(id: string): AppResponse<void> {
  return httpService.delete(`/singer/${id}`)
}
