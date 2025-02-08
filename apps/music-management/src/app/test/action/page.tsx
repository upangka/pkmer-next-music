'use client'
import { deleteMusic } from './actions'
import { Button } from '@pkmer-music/management/components/ui/button'

/**
 * 测试server action
 * @returns
 */
export default function Page() {
  return (
    <>
      <Button
        onClick={async () => {
          const id = await deleteMusic('6477a7b7d9e8b9d9e8b9d9e8')
          console.log(id)
        }}
      >
        Button
      </Button>
    </>
  )
}
