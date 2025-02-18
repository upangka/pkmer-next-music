'use client'
import { useEffect, useRef, useState } from 'react'
import { useRouter } from 'next/navigation'
import { useImmer } from 'use-immer'
import { format } from 'date-fns'
import { PkmerForm, PkmerFormItem, PkmerItemError } from '@pkmer-music/management/components'

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
import { Textarea } from '@pkmer-music/management/components/ui/textarea'
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue
} from '@pkmer-music/management/components/ui/select'
import { RadioGroup, RadioGroupItem } from '@pkmer-music/management/components/ui/radio-group'

import { PkmerDialog } from './pkmer-dialog'
import { SexEnum, type SingerBase } from '@pkmer-music/management/types'
import { AREA } from '@pkmer/libs/constants'
import { addSinger } from '@pkmer-music/management/actions'
import useConfetti from '@pkmer-music/management/hooks/use-confetti'
import { ToastAction } from '@pkmer-music/management/components/ui/toast'
import { useToast } from '@pkmer-music/management/hooks/use-toast'
import z from 'zod'

interface AddSingerProps {}
// 默认的歌手信息
const defaultSingerInfo: SingerBase = {
  name: '',
  sex: SexEnum.UNKNOWN,
  pic: '',
  birth: '',
  location: '',
  introduction: ''
}

const FormScheme = z.object({
  name: z.string().min(1, { message: 'name is required' }),
  birth: z.string().min(1, { message: 'birth is required' }),
  location: z.string().min(1, { message: 'location is required' }),
  introduction: z.string().min(1, { message: 'introduction is required' })
})

type ValidFormStatus = {
  errors?: {
    name?: string[] | undefined
    birth?: string[] | undefined
    location?: string[] | undefined
    introduction?: string[] | undefined
  }
}

export const AddSinger: React.FC<AddSingerProps> = props => {
  const router = useRouter()

  const { toast } = useToast()
  const { showConfetti } = useConfetti()
  const [isOpen, setIsOpen] = useState(false)
  const [singer, setSinger] = useImmer<SingerBase>(defaultSingerInfo)
  const [validFormStatus, setValidFormStatus] = useState<ValidFormStatus>({})
  const isSubmitRef = useRef(false)

  useEffect(() => {
    // 由于这里是通过css来隐藏dialog的，组件并没有真正的卸载，
    // 所以每当关闭的时候需要将歌手的信息重置
    if (!isOpen) {
      isSubmitRef.current && refreshPage()
      // reset data
      setSinger(defaultSingerInfo)
      isSubmitRef.current = false
    }
  }, [isOpen])

  useEffect(() => {
    // 处理错误之后，需要将错误信息清空
    if (validFormStatus.errors) {
      const validateFields = FormScheme.safeParse(singer)
      if (!validateFields.success) {
        setValidFormStatus({
          errors: validateFields.error.flatten().fieldErrors
        })
      } else {
        setValidFormStatus({})
      }
    }
  }, [singer]) // 监听表单数据变化,当表单数据变化时，重新校验表单

  async function handleFormSubmit(e: React.SyntheticEvent) {
    e.preventDefault()
    // 表单校验
    const validateFields = FormScheme.safeParse(singer)
    if (!validateFields.success) {
      toast({
        title: '表单验证失败',
        description: '请仔细检查未填写的数据'
      })
      setValidFormStatus({
        errors: validateFields.error.flatten().fieldErrors
      })
      return
    }
    isSubmitRef.current = true
    await addSinger(singer)
    setIsOpen(false)
    showPrompt()
  }

  function showPrompt() {
    showConfetti()
    showToast()
  }

  function refreshPage() {
    setTimeout(() => {
      router.refresh() // 延迟刷新页面
    }, 100) // 适当延迟，确保对话框关闭后再刷新
  }

  function showToast() {
    toast({
      title: '添加歌手成功',
      description: `添加${singer.name}歌手成功`,
      action: <ToastAction altText='添加成功'>添加成功</ToastAction>
    })
  }

  return (
    <PkmerDialog isOpen={isOpen} onOpenChange={setIsOpen} title='添加歌手'>
      <PkmerForm onSubmit={handleFormSubmit} className='gap-7'>
        {/* 姓名start */}
        <PkmerFormItem
          label='姓名'
          id='username-error'
          errorPrompt={<PkmerItemError errors={validFormStatus.errors?.name} />}
        >
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
        {/* 地区start */}
        <PkmerFormItem
          label='地区'
          errorPrompt={<PkmerItemError errors={validFormStatus.errors?.location} />}
        >
          <Select
            onValueChange={value =>
              setSinger(draft => {
                draft.location = value
              })
            }
            defaultValue={AREA.find(area => area.value === singer.location)?.value}
          >
            <SelectTrigger className='w-[180px]'>
              <SelectValue placeholder='选择地区' />
            </SelectTrigger>
            <SelectContent>
              <SelectGroup>
                <SelectLabel>地区</SelectLabel>
                {AREA.map(area => (
                  <SelectItem key={area.value} value={area.value}>
                    {area.label}
                  </SelectItem>
                ))}
              </SelectGroup>
            </SelectContent>
          </Select>
        </PkmerFormItem>
        {/* 地区end */}

        {/* 生日start */}
        <PkmerFormItem
          label='生日'
          errorPrompt={<PkmerItemError errors={validFormStatus.errors?.birth} />}
        >
          <Popover>
            <PopoverTrigger asChild>
              <Button
                variant={'outline'}
                className={cn(
                  'w-[180px] justify-start text-left font-normal',
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

        {/* 简介start */}
        <PkmerFormItem
          label='简介'
          errorPrompt={<PkmerItemError errors={validFormStatus.errors?.introduction} />}
        >
          <Textarea
            placeholder='请输入简介'
            value={singer.introduction}
            onChange={e =>
              setSinger(draft => {
                draft.introduction = e.target.value
              })
            }
          />
        </PkmerFormItem>
        {/* 简介end */}

        <div>
          <Button type='submit' className='w-full'>
            提交
          </Button>
        </div>
      </PkmerForm>
    </PkmerDialog>
  )
}
