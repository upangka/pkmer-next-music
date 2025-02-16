/**
 * 计算百分比并格式化为指定小数位数
 * @param current 当前值
 * @param total 总值
 * @param decimals 小数位数（默认为 2，只取整数部分）
 * @returns 格式化后的百分比字符串（如 "75.00%"）
 */
export const calculatePercentage = (
  current: number,
  total: number,
  decimals: number = 2
): string => {
  if (total === 0) {
    return '0.00%' // 避免除以零的情况
  }

  // 只取 decimals 的整数部分
  const validDecimals = Math.floor(decimals)

  return ((current / total) * 100).toFixed(validDecimals) + '%'
}
