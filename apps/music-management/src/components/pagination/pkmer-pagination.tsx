'use client'
import { useSearchParams, usePathname } from 'next/navigation'
import {
  Pagination,
  PaginationContent,
  PaginationEllipsis,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious
} from '@pkmer-music/management/components/ui/pagination'
import { JSX } from 'react'
import clsx from 'clsx'
import paginationStyle from './pkmer-pagination.module.scss'
interface PaginationProps {
  total: number
}

export const PKMPagination: React.FC<PaginationProps> = props => {
  const searchParams = useSearchParams()
  const pathname = usePathname()
  const currentPage = +(searchParams.get('pageNo') ?? 1)
  // 使用router.push来代替link组件的href属性，因为它会reload windows
  function createhref(page: number) {
    const params = new URLSearchParams(searchParams)
    params.set('pageNo', page.toString())
    return `${pathname}?${params.toString()}`
  }

  const getPageNumbers = () => {
    const pageNumbers: JSX.Element[] = []
    const maxVisiblePages = 5

    // 如果总页数小于或等于最大可见页数
    if (props.total <= maxVisiblePages) {
      // 生成从1到总页数的页码
      for (let i = 1; i <= props.total; i++) {
        pageNumbers.push(
          <PaginationItem key={i}>
            <PaginationLink
              href={createhref(i)}
              className={clsx(currentPage === i && paginationStyle.active)}
            >
              {i}
            </PaginationLink>
          </PaginationItem>
        )
      }
    } else {
      // 计算可见页码的左右边界
      const left = Math.max(1, currentPage - 2)
      const right = Math.min(props.total, currentPage + 2)

      // 如果左边界大于1，添加第一页和省略号
      if (left > 1) {
        pageNumbers.push(
          <PaginationItem key='first'>
            <PaginationLink
              href={createhref(1)}
              className={clsx(currentPage === 1 && paginationStyle.active)}
            >
              1
            </PaginationLink>
          </PaginationItem>
        )
        pageNumbers.push(
          <PaginationItem key='ellipsis-left'>
            <PaginationEllipsis />
          </PaginationItem>
        )
      }

      // 生成左边界和右边界之间的可见页码
      for (let i = left; i <= right; i++) {
        pageNumbers.push(
          <PaginationItem key={i}>
            <PaginationLink
              href={createhref(i)}
              className={clsx(currentPage === i && paginationStyle.active)}
            >
              {i}
            </PaginationLink>
          </PaginationItem>
        )
      }

      // 如果右边界小于总页数，添加省略号和最后一页
      if (right < props.total) {
        pageNumbers.push(
          <PaginationItem key='ellipsis-right'>
            <PaginationEllipsis />
          </PaginationItem>
        )
        pageNumbers.push(
          <PaginationItem key='last'>
            <PaginationLink
              href={createhref(props.total)}
              className={clsx(currentPage === props.total && paginationStyle.active)}
            >
              {props.total}
            </PaginationLink>
          </PaginationItem>
        )
      }
    }

    return pageNumbers
  }

  return (
    <section className='py-3'>
      <Pagination>
        <PaginationContent className='flex gap-5'>
          <PaginationItem>
            <PaginationPrevious
              href={currentPage > 1 ? createhref(currentPage - 1) : ''}
              className={clsx(
                'select-none rounded px-3 py-2',
                currentPage === 1 && 'cursor-not-allowed bg-gray-300 text-gray-500'
              )}
            />
          </PaginationItem>
          {getPageNumbers()}
          <PaginationItem>
            <PaginationNext
              href={currentPage < props.total ? createhref(currentPage + 1) : ''}
              className={clsx(
                'select-none rounded px-3 py-2',
                currentPage >= props.total &&
                  'pointer-events-none cursor-not-allowed bg-gray-300 text-gray-500'
              )}
            />
          </PaginationItem>
        </PaginationContent>
      </Pagination>
    </section>
  )
}
