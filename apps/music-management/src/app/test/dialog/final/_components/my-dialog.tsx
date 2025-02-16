import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
  DialogClose
} from '@pkmer-music/management/components/ui/dialog'
import { Button } from '@pkmer-music/management/components/ui/button'
import { useState } from 'react'
interface Props {}
const MyDialog: React.FC<Props> = () => {
  const [showUpload, setShowUpload] = useState(false)

  return (
    <>
      <Dialog open={true}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle className='text-center'>更新歌曲</DialogTitle>
          </DialogHeader>

          {/* {showUpload ? <p>上传中</p> : <p>主要内容</p>} */}
          {showUpload && <p>上传中</p>}
          {!showUpload && (
            <>
              <p className='grid h-[500px] place-items-center'>主要内容</p>
              <Button
                variant='outline'
                onClick={async () => {
                  setShowUpload(true)
                  await new Promise(resolve => {
                    setTimeout(resolve, 3000)
                  })
                }}
              >
                上传
              </Button>
            </>
          )}

          {/* <DialogFooter className='justify-end'>
            <Button
              variant='outline'
              onClick={() => {
                setShowUpload(true)
              }}
            >
              上传
            </Button>
            <Button
              variant='secondary'
              onClick={() => {
                setShowUpload(false)
              }}
            >
              取消
            </Button>
          </DialogFooter> */}
        </DialogContent>
      </Dialog>
    </>
  )
}

export default MyDialog
