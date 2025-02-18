'use client'
import { useState } from 'react'
import { useImmer } from 'use-immer'
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
import { Input } from '@pkmer-music/management/components/ui/input'
import { Label } from '@pkmer-music/management/components/ui/label'
import { RadioGroup, RadioGroupItem } from '@pkmer-music/management/components/ui/radio-group'

import { PkmerDialog } from './pkmer-dialog'
import { SexEnum, type SingerBase } from '@pkmer-music/management/types'
import { AREA } from '@pkmer/libs/constants'

interface AddSingerProps {}
export const AddSinger: React.FC<AddSingerProps> = props => {
  const [singer, setSinger] = useImmer<SingerBase>({
    name: '',
    sex: SexEnum.UNKNOWN,
    pic: '',
    birth: '',
    location: '',
    introduction: ''
  })
  return (
    <PkmerDialog title='添加歌手'>
      <PkmerForm className='gap-7'>
        {/* 姓名start */}
        <PkmerFormItem label='姓名'>
          <Input
            placeholder='请输入姓名'
            value={singer.name}
            onChange={e =>
              setSinger(draft => {
                draft.name = e.target.value
              })
            }
          />
        </PkmerFormItem>
        {/* 姓名end */}
        {/* 性别start */}
        <PkmerFormItem label='性别'>
          <RadioGroup
            onValueChange={value =>
              setSinger(draft => {
                draft.sex = +value as SexEnum
              })
            }
            defaultValue={(singer.sex ?? SexEnum.UNKNOWN).toString()}
            className='flex flex-row gap-5'
          >
            <div className='flex items-center space-x-2'>
              <RadioGroupItem value={SexEnum.FEMALE.toString()} id='female' />
              <Label htmlFor='female'>女</Label>
            </div>
            <div className='flex items-center space-x-2'>
              <RadioGroupItem value={SexEnum.MALE.toString()} id='male' />
              <Label htmlFor='male'>男</Label>
            </div>
            <div className='flex items-center space-x-2'>
              <RadioGroupItem value={SexEnum.UNKNOWN.toString()} id='unknown' />
              <Label htmlFor='unknown'>未知</Label>
            </div>
          </RadioGroup>
        </PkmerFormItem>
        {/* 性别end */}
        {/* 生日start */}
        <PkmerFormItem label='生日'>
          <Popover>
            <PopoverTrigger asChild>
              <Button
                variant={'outline'}
                className={cn(
                  'w-full justify-start text-left font-normal',
                  !singer.birth && 'text-muted-foreground'
                )}
              >
                <CalendarIcon className='mr-2 h-4 w-4' />
                {singer.birth ? singer.birth : <span>Pick a date</span>}
              </Button>
            </PopoverTrigger>
            <PopoverContent className='w-auto p-0'>
              <Calendar
                mode='single'
                selected={singer.birth ? new Date(singer.birth) : undefined}
                onSelect={date => {
                  if (date) {
                    setSinger(draft => {
                      draft.birth = format(date, 'yyyy-MM-dd')
                    })
                  }
                }}
                initialFocus
                required
              />
            </PopoverContent>
          </Popover>
        </PkmerFormItem>
        {/* 生日end */}
      </PkmerForm>
    </PkmerDialog>
  )
}
