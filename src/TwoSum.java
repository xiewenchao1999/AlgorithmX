package src;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标元素 target，
 * 请在数组中搜索“和”为 target 的两个元素，
 * 并返回他们的数组索引。
 * 返回任意一个解即可。
 */
public class TwoSum {
    /**
     * 暴力枚举
     * @param nums  数组
     * @param target    目标元素
     * @return  索引
     */
    int[] twoSumBruteForce(int[] nums, int target) {
        int size = nums.length;
        // 两层循环，时间复杂度为 O(n^2)
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 辅助哈希表
     * @param nums  数组
     * @param target    目标元素
     * @return  索引
     */
    int[] twoSumHashTable(int[] nums, int target) {
        int size = nums.length;
        // 辅助哈希表，空间复杂度为 O(n)
        Map<Integer, Integer> dic = new HashMap<>();
        // 单层循环，时间复杂度为 O(n)
        for (int i = 0; i < size; i++) {
            if (dic.containsKey(target - nums[i])) {
                return new int[]{dic.get(target - nums[i]), i};
            }
            dic.put(nums[i], i);
        }
        return new int[0];
    }
}
