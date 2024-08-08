package src;

import java.util.concurrent.ThreadLocalRandom;

public class array {
    int[] arr = new int[10];
    int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    /**
     * 随机访问元素 O(1)
     * @param nums 数组
     * @return 随机元素
     */
    int randomAccess(int[] nums) {
        // 在索引区间[0, nums.length)随机抽取一个索引数字
        int randomIndex = ThreadLocalRandom.current().nextInt(0, nums.length);
        // 获取并返回随机元素
        return nums[randomIndex];
    }

    /**
     * 在数组索引index处插入元素 O(n)
     * @param nums  数组
     * @param num   插入元素
     * @param index 插入元素索引
     */
    void insert(int[] nums, int num, int index) {
        // 将索引index及其后所有元素向后移动一位
        for (int i = nums.length - 1; i > index; i--) {
            nums[i] = nums[i - 1];
        }
        // 将num赋给索引index处的元素
        nums[index] = num;
    }

    /**
     * 删除索引index处的元素 O(n)
     * @param nums 数组
     * @param index 删除元素索引
     */
    void delete(int[] nums, int index) {
        // 将索引index其后所有元素向前移动一位
        for (int i = index; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
    }

    /**
     * 遍历数组-累加 O(n)
     * @param nums  数组
     */
    void traverse(int[] nums) {
        int count = 0;
        // 通过索引遍历数组
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
        }
        // 直接遍历数组元素
        for (int num : nums) {
            count += num;
        }
    }

    /**
     * 在数组中查找指定元素 O(n)
     * @param nums  数组
     * @param target    指定元素
     * @return  指定元素索引
     */
    int find(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 扩展数组长度
     * @param nums  数组
     * @param enlarge   扩展大小
     * @return  扩展后的数组
     */
    int[] extend(int[] nums, int enlarge) {
        // 初始化一个扩展后的数组
        int[] res = new int[nums.length + enlarge];
        // 将原数组中的所有元素复制到新数组中
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }
        // 返回新数组
        return res;
    }
}
