package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    /**
     * 桶排序
     * @param nums  数组
     */
    void bucketSort(float[] nums) {
        // 初始化 k = n/2 个桶，预期向每个桶分配 2 个元素
        int k = nums.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<>());
        }
        // 1. 将数组元素分配到各个桶中
        for (float num : nums) {
            // 输入数据范围为 [0, 1)，使用 num * k 映射到索引范围 [0, k-1]
            int i = (int) (num * k);
            // 将 num 添加进桶 i
            buckets.get(i).add(num);
        }
        // 2. 对各个桶执行排序
        for (List<Float> bucket : buckets) {
            // 使用内置排序函数（也可替换为其他排序算法）
            Collections.sort(bucket);
        }
        // 3. 遍历桶，合并结果
        int i = 0;
        for (List<Float> bucket : buckets) {
            for (Float num : bucket) {
                nums[i++] = num;
            }
        }
    }
}
