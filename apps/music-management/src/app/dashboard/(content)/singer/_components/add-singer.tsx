'use client'
import { useState } from 'react'
import { format } from 'date-fns'
import { PkmerForm, PkmerFormItem } from '@pkmer-music/management/components'

import { Calendar as CalendarIcon } from 'lucide-react'

import { cn } from '@pkmer-music/management/lib/utils'
import { Button } from '@pkmer-music/management/components/ui/button'
import { Calendar } from '@pkmer-music/management/components/ui/calendar'
import {
  Popover,
  PopoverContent,
  PopoverTrigger
} from '@pkmer-music/management/components/ui/popover'

import { PkmerDialog } from './pkmer-dialog'

interface AddSingerProps {}
export const AddSinger: React.FC<AddSingerProps> = props => {
  const [date, setDate] = useState<Date>()

  return (
    <PkmerDialog title='添加歌手'>
      <PkmerForm>
        {/* 生日start */}
        <PkmerFormItem label='生日'>
          <Popover>
            <PopoverTrigger asChild>
              <Button
                variant={'outline'}
                className={cn(
                  'w-[280px] justify-start text-left font-normal',
                  !date && 'text-muted-foreground'
                )}
              >
                <CalendarIcon className='mr-2 h-4 w-4' />
                {date ? format(date, 'yyyy-MM-dd') : <span>Pick a date</span>}
              </Button>
            </PopoverTrigger>
            <PopoverContent className='w-auto p-0'>
              <Calendar mode='single' selected={date} onSelect={setDate} initialFocus required />
            </PopoverContent>
          </Popover>
        </PkmerFormItem>
        {/* 生日end */}
      </PkmerForm>
    </PkmerDialog>
  )
}
