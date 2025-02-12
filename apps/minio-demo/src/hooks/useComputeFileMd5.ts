import { useState, useRef } from 'react'
import CryptoJS from 'crypto-js'

export default function useComputeFileMd5() {
  const [fileMd5, setFileMd5] = useState<string>('')
  const chunkCountRef = useRef(0) // 更明确的命名

  function clear() {
    setFileMd5('')
    chunkCountRef.current = 0
  }

  function computeFileMd5(file: File): Promise<string> {
    const reader = new FileReader()
    const md5 = CryptoJS.algo.MD5.create()
    const chunkSize = 2 * 1024 * 1024 // 2MB
    let offset = 0

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
          setFileMd5(hash)
          resolve(hash)
        }
      }

      reader.onerror = function (error) {
        reject(error)
      }

      function readNextChunk() {
        if (offset >= file.size) return // 确保不会读取超出文件范围
        const sliceFile = file.slice(offset, offset + chunkSize)
        reader.readAsArrayBuffer(sliceFile)
        console.log(`读取第${chunkCountRef.current + 1}分片成功`)
        chunkCountRef.current++
      }

      readNextChunk()
    })
  }

  return { fileMd5, computeFileMd5, clear }
}
