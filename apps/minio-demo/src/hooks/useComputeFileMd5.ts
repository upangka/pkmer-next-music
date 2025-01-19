import { useState } from 'react'
import CryptoJS from 'crypto-js'

export default function useComputeFileMd5() {
  const [fileMd5, setFileMd5] = useState<string>('')

  function clear() {
    setFileMd5('')
  }

  function computeFileMd5(file: File): Promise<string> {
    const reader = new FileReader()
    const md5 = CryptoJS.algo.MD5.create()
    let offset = 0
    let count = 1 // 加载文件次数
    const chunkSize = 2 * 1024 * 1024 // 2MB

    function readNextChunk() {
      console.log('加载文件', count++, '部分')
      const sliceFile = file.slice(offset, offset + chunkSize)
      reader.readAsArrayBuffer(sliceFile)
    }

    readNextChunk()

    return new Promise((resolve, reject) => {
      reader.onload = function (event) {
        const arrayBuffer = event.target?.result as ArrayBuffer
        const wordArray = CryptoJS.lib.WordArray.create(arrayBuffer)
        md5.update(wordArray)

        offset += chunkSize
        if (offset < file.size) {
          readNextChunk()
        } else {
          const hash = md5.finalize().toString()
          console.log('文件的MD5', hash)
          setFileMd5(hash)
          resolve(hash)
        }
      }

      reader.onerror = function (error) {
        reject(error)
      }
    })
  }

  return { fileMd5, computeFileMd5, clear }
}
