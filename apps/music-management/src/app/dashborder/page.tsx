import PkmerIcon from '@pkmer-music/management/components/icon/PkmerIcon'
import { AlertCircle } from 'lucide-react'
import { Alert, AlertDescription, AlertTitle } from '@pkmer-music/management/components/ui/alert'

export default function DashBorder() {
  return (
    <>
      {/* header start */}
      <section className='bg-red-500'>
        <PkmerIcon icon='mynaui:menu-solid' />
      </section>
      {/* header end */}

      <Alert variant='destructive'>
        <AlertCircle className='h-4 w-4' />
        <AlertTitle>Error</AlertTitle>
        <AlertDescription>Your session has expired. Please log in again.</AlertDescription>
      </Alert>
    </>
  )
}
