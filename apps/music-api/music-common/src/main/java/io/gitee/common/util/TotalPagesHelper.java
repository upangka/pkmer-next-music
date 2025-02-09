package io.gitee.common.util;

public interface TotalPagesHelper {

    /**
     * 计算总页数
     * 该方法用于根据总记录数和每页大小来计算总页数
     * 通过向上取整的方式确保所有记录都能被包含在内，避免因页数计算不准确导致的数据遗漏
     *
     * @param total 总记录数，表示需要分页处理的数据总量
     * @param pageSize 每页大小，表示每页可以容纳的记录数
     * @return 总页数，计算结果为包含所有记录所需的最小页数
     */
    static int calcTotalPages(int total, int pageSize) {
        return (total + pageSize - 1) / pageSize;
    }

}
