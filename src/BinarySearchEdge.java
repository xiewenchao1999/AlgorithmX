package src;

/**
 * 二分查找边界
 */
public class BinarySearchEdge {
    BinarySearchInsertion insertion;

    /**
     * 二分查找最左一个 target
     * @param nums  数组
     * @param target    目标元素
     * @return  索引，若不包含元素返回 -1
     */
    int binarySearchLeftEdge(int[] nums, int target) {
        // 等价于查找 target 的插入点
        int i = insertion.binarySearchInsertion(nums, target);
        // 未找到 target，则插入点的索引 i 越界、或元素 nums[i] 与 target 不相等，返回 -1
        if (i == nums.length || nums[i] != target) {
            return -1;
        }
        // 找到 target，返回索引 i
        return i;
    }

    /**
     * 二分查找最右一个 target
     * @param nums  数组
     * @param target    目标元素
     * @return  索引，若不包含元素返回 -1
     */
    int binarySearchRightEdge(int[] nums, int target) {
        // 可转化为查找最左一个 target + 1
        int i = insertion.binarySearchInsertion(nums, target + 1);
        // j 指向最右一个 target，i 指向首个大于 target 的元素
        int j = i - 1;
        // 未找到 target，返回 -1
        if (j == -1 || nums[j] != target) {
            return -1;
        }
        // 找到 target，返回索引 i
        return j;
    }
}
