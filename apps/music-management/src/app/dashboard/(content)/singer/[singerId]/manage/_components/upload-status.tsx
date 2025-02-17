interface UploadStatusProps {
  uploaded: string // 上传进度 如10%
  showText?: boolean // 是否显示文字
}

export const UploadStatus: React.FC<UploadStatusProps> = ({ uploaded, showText = false }) => {
  const style: React.CSSProperties = {
    width: uploaded
  }

  return (
    <section className='w-full'>
      {showText && <h1 className='p-2 text-gray-500'>{uploaded}</h1>}
      <div className='relative h-[20px] w-full bg-gray-200'>
        <div
          style={style}
          className='absolute h-full rounded-r-lg bg-green-500 transition-all duration-300 ease-in-out'
        ></div>
      </div>
    </section>
  )
}
