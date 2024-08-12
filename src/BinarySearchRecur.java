package src;

/**
 * 给定一个长度为 n 的有序数组 nums ，其中所有元素都是唯一的，请查找元素 target 。
 */
public class BinarySearchRecur {
    /**
     * 二分查找：问题 f(i, j)
     * @param nums      数组
     * @param target    目标值
     * @param i         左边界索引
     * @param j         右边界索引
     * @return          递归到的目标值索引
     */
    int dfs(int[] nums, int target, int i, int j) {
        // 若区间为空，代表无目标元素，则返回 -1
        if (i > j)
            return -1;

        // 计算中点索引 m
        int m = (i + j) / 2;
        if (nums[m] < target)
            // 递归子问题 f(m+1, j)
            return dfs(nums, target, m + 1, j);
        else if (nums[m] > target)
            // 递归子问题 f(i, m-1)
            return dfs(nums, target, i, m - 1);
        else
            // 找到目标元素，返回其索引
            return m;
    }

    /**
     * 二分查找
     * @param nums      数组
     * @param target    目标元素
     * @return          目标元素索引
     */
    int binarySearch(int[] nums, int target) {
        int n = nums.length;
        // 求解问题
        return dfs(nums, target, 0, n - 1);
    }
}
