package src;

import java.util.Arrays;

public class MergeSort {
    /**
     * 合并左子数组和右子数组
     * @param nums  数组
     * @param left  左索引
     * @param mid   中索引
     * @param right 右索引
     */
    void merge(int[] nums, int left, int mid, int right) {
        // 左子数组区间 [left, mid]
        // 右子数组区间 [mid + 1, right]
        // 创建一个临时数组 tmp，用于存放合并后的结果
        int[] tmp = new int[right - left + 1];
        // 初始化左子数组和右子数组的起始索引
        int i = left, j = mid + 1, k = 0;
        // 当左右子数组都还有元素时，进行比较，并将较小的元素复制到临时数组中
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j])
                tmp[k++] = nums[i++];
            else
                tmp[k++] = nums[j++];
        }
        // 将左子数组和右子数组的剩余元素复制到临时数组中
        while (i <= mid)
            tmp[k++] = nums[i++];
        while (j <= right)
            tmp[k++] = nums[j++];
        // 将临时数组 tmp 中的元素复制回原数组 nums 的对应区间
        for (k = 0; k < tmp.length; k++) {
            nums[left + k] = tmp[k];
        }
    }

    void mergeSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    public static void main(String[] args) {
        int[] nums = {7, 3, 2, 6, 0, 1, 5, 4};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
