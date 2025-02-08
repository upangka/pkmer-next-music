'use client'
import { useSearchParams } from 'next/navigation'
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

const PKMPagination: React.FC<PaginationProps> = props => {
  const searchParams = useSearchParams()
  const currentPage = +(searchParams.get('page') ?? 1)

  const getPageNumbers = () => {
    const pageNumbers: JSX.Element[] = []
    const maxVisiblePages = 5

    // 如果总页数小于或等于最大可见页数
    if (props.total <= maxVisiblePages) {
      // 生成从1到总页数的页码
      for (let i = 1; i <= props.total; i++) {
        pageNumbers.push(
          <PaginationItem key={i}>
            <PaginationLink href='#' className={clsx(currentPage === i && paginationStyle.active)}>
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
            <PaginationLink href='#' className={clsx(currentPage === 1 && paginationStyle.active)}>
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
            <PaginationLink href='#' className={clsx(currentPage === i && paginationStyle.active)}>
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
              href='#'
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
        <PaginationContent>
          <PaginationItem>
            <PaginationPrevious href='#' />
          </PaginationItem>
          {getPageNumbers()}
          <PaginationItem>
            <PaginationNext href='#' />
          </PaginationItem>
        </PaginationContent>
      </Pagination>
    </section>
  )
}

export default PKMPagination
