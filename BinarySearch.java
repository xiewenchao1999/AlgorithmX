/**
 * 二分查找
 */
public class BinarySearch {
    /**
     * 二分查找（双闭区间）
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     * @param nums  数组
     * @param target    目标查找值
     * @return  找到目标值，返回其索引，否则返回 -1
     */
    int binarySearch(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1]，即 i，j 分别指向数组首元素、尾元素
        int i = 0, j = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 i > j 时为空）
        while (i <= j) {
            // 计算中点索引 m（避免大数越界的计算公式）
            int m = i + (j - i) / 2;
            // 此情况说明 target 在区间 [m+1, j] 中
            if (nums[m] < target) {
                i = m + 1;
                // 此情况说明 target 在区间 [i, m-1] 中
            } else if (nums[m] > target) {
                j = m - 1;
                // 找到目标元素，返回其索引
            } else {
                return m;
            }
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

    /**
     * 二分查找（左闭右开区间）
     * @param nums  数组
     * @param target    目标查找值
     * @return  找到目标值，返回其索引，否则返回 -1
     */
    int binarySearchLCRO(int[] nums, int target) {
        // 初始化左闭右开区间 [0, n)，即 i，j 分别指向数组首元素、尾元素+1
        int i = 0, j = nums.length;
        // 循环，当搜索区间为空时跳出
        while (i < j) {
            // 计算中点索引 m
            int m = i + (j - i) / 2;
            // 此情况说明 target 在区间 [m+1, j) 中
            if (nums[m] < target) {
                i = m + 1;
                // 此情况说面 target 在区间 [i, m) 中
            } else if (nums[m] > target) {
                j = m;
                // 找到目标元素，返回其索引
            } else {
                return m;
            }
        }
        // 未找到目标元素，返回 -1
        return -1;
    }
}
