package src;

/**
 * 插入排序
 */
public class InsertionSort {
    /**
     * 插入排序
     * @param nums  数组
     */
    void insertionSort(int[] nums) {
        // 外循环：已排序区间为 [0, i-1]
        for (int i = 1; i < nums.length; i++) {
            int base = nums[i], j = i - 1;
            // 内循环：将 base 插入到已排序区间 [0, i-1] 中的正确位置
            while (j >= 0 && nums[j] > base) {
                // 将 nums[j] 向右移动一位
                nums[j + 1] = nums[j];
                j--;
            }
            // 将 base 赋值到正确位置
            nums[j + 1] = base;
        }
    }
}
