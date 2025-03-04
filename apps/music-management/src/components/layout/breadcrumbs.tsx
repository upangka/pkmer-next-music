import clsx from 'clsx'
import Link from 'next/link'
import type { BreadCrumb } from '@pkmer-music/management/types'

interface Props {
  breadCrumbs: BreadCrumb[]
}

export const BreadCrumbs: React.FC<Props> = ({ breadCrumbs }) => {
  if (breadCrumbs.length === 0) return null

  return (
    <nav className='w-fit rounded-md border px-5 py-2'>
      <ol className='flex items-center justify-start'>
        {breadCrumbs.map((breadcrumb, index, arr) => (
          <li key={index}>
            <Link
              href={breadcrumb.href}
              className={clsx(
                'text-gray-500',
                breadcrumb.active &&
                  'pointer-events-none cursor-not-allowed font-bold text-gray-900'
              )}
              style={breadcrumb.active ? { cursor: 'not-allowed' } : {}}
            >
              {breadcrumb.label}
            </Link>
            {index !== arr.length - 1 && <span className='mx-3'>/</span>}
          </li>
        ))}
      </ol>
    </nav>
  )
}
