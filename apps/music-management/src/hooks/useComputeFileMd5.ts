import { useState, useRef } from 'react'
import CryptoJS from 'crypto-js'

export default function useComputeFileMd5() {
  const [isComputeFileFinished, setIsComputeFileFinished] = useState(false)
  const [fileMd5, setFileMd5] = useState<string>('')
  const chunkCountRef = useRef(0) // 定义计算文件分片的数量
  // 文件分片的总数，默认为1,防止除数为0报错
  const [totalParts, setTotalParts] = useState<number>(1)
  // 处理完成的分片数量
  const [completedParts, setCompletedParts] = useState<number>(0)

  function clear() {
    setFileMd5('')
    chunkCountRef.current = 0
  }

  /**
   * 计算文件分片的总数
   * @param fileSize  文件的大小
   * @param chunkSize  分块大小
   * @returns
   */
  function calculateTotalParts(fileSize: number, chunkSize: number) {
    const total = Math.ceil(fileSize / chunkSize)
    setTotalParts(total)
  }

  function reset() {
    chunkCountRef.current = 0
    setCompletedParts(0)
    setTotalParts(1)
    setIsComputeFileFinished(false)
  }

  function computeFileMd5(file: File): Promise<string> {
    const reader = new FileReader()
    const md5 = CryptoJS.algo.MD5.create()
    const chunkSize = 10 * 1024 * 1024 // 10MB
    let offset = 0
    // 计算文件分片的总数
    calculateTotalParts(file.size, chunkSize)

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
          setIsComputeFileFinished(true)
          reset()
          resolve(hash)
        }
      }

      reader.onerror = function (error) {
        reject(error)
      }

      function readNextChunk() {
        if (offset >= file.size) return // 确保不会读取超出文件范围

        // 确保即使文件大小不是块大小的整数倍，最后一块也会被正确读取。
        // 如果文件大小不是块大小的整数倍，则读取剩余的部分。
        const sliceFile = file.slice(offset, offset + chunkSize)
        reader.readAsArrayBuffer(sliceFile)
        console.log(`读取第${chunkCountRef.current + 1}分片成功`)
        chunkCountRef.current++
        setCompletedParts(chunkCountRef.current)
      }

      readNextChunk()
    })
  }

  return { totalParts, completedParts, fileMd5, isComputeFileFinished, computeFileMd5, clear }
}
