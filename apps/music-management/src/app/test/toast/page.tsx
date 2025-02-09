'use client'

import { useToast } from '@pkmer-music/management/hooks/use-toast'
import { Button } from '@pkmer-music/management/components/ui/button'
import { ToastAction } from '@pkmer-music/management/components/ui/toast'

export default function PAge() {
  const { toast } = useToast()

  return (
    <Button
      variant='outline'
      onClick={() => {
        toast({
          variant: 'destructive',
          title: 'Uh oh! Something went wrong.',
          description: 'There was a problem with your request.',
          action: <ToastAction altText='Try again'>Try again</ToastAction>
        })
      }}
    >
      Show Toast
    </Button>
  )
}
