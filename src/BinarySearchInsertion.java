package src;

/**
 * 二分查找插入点
 */
public class BinarySearchInsertion {

    /**
     * 二分查找插入点（无重复元素）
     * @param nums  数组
     * @param target    目标插入元素
     * @return  插入点索引
     */
    int binarySearchInsertionSimple(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1]
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            // 计算中点索引
            int m = i + (j - i) / 2;
            // target 在区间 [m+1, j] 中
            if (nums[m] < target)
                i = m + 1;
            // target 在区间 [i, m-1] 中
            else if (nums[m] > target)
                j = m - 1;
            // 找到与 target 等值元素的索引，返回插入点索引 m
            else
                return m;
        }
        // 未找到与 target 等值的元素索引，返回插入点索引 i
        return i;
    }

    /**
     * 二分查找插入点（存在重复元素）
     * @param nums  数组
     * @param target    目标插入元素
     * @return  插入点索引
     */
    int binarySearchInsertion(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1]
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            // 计算中点索引
            int m = i + (j - i) / 2;
            // target 在区间 [m+1, j] 中
            if (nums[m] < target)
                i = m + 1;
            // target 在区间 [i, m-1] 中
            else if (nums[m] > target)
                j = m - 1;
            // 首个小于 target 的元素在区间 [i, m-1] 中
            else
                j = m - 1;
        }
        // 返回插入点 i
        return i;
    }
}

