interface PkmerItemErrorProps {
  errors: string[] | undefined
}

export const PkmerItemError: React.FC<PkmerItemErrorProps> = ({ errors }) => {
  return (
    <div aria-atomic='true' aria-live='polite'>
      {errors &&
        errors.map(error => (
          <p key={error} className='mt-1 text-sm text-red-500'>
            {error}
          </p>
        ))}
    </div>
  )
}
