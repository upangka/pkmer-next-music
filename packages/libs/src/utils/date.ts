/**
 * Formats a given Date object to a string in the format YYYY-MM-DD.
 *
 * @param {Date} date - The date to format.
 * @returns {string} The formatted date string.
 */
export function formatDate(date: Date): string {
  // Convert the date to ISO string format and split at 'T' to separate date and time
  // Return the date part (first element of the split array)
  return date.toISOString().split('T')[0]
}
