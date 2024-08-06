import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxHeap {
    List<Integer> maxHeap = new ArrayList<>();

    // 获取左子节点的索引
    int left(int i) {
        return 2 * i + 1;
    }

    // 获取右子节点的索引
    int right(int i) {
        return 2 * i + 2;
    }

    // 获取父节点的索引
    int parent(int i) {
        return (i - 1) / 2;
    }

    // 访问堆顶元素
    int peek() {
        return maxHeap.get(0);
    }

    // 元素入堆
    void push(int val) {
        // 添加节点
        maxHeap.add(val);
        // 从底至顶堆化
        siftUp(size() - 1);
    }

    // 从节点 i 开始，从底至顶堆化
    void siftUp(int i) {
        while (true) {
            // 获取节点 i 的父节点
            int p = parent(i);
            // 当“越过根节点”或“节点无须修复”时，结束堆化
            if (p < 0 || maxHeap.get(i) <= maxHeap.get(p)) {
                break;
            }
            // 交换节点
            swap(i, p);
            // 循环向上堆化
            i = p;
        }
    }

    // 堆大小
    int size() {
        return maxHeap.size();
    }

    // 交换节点
    void swap(int i, int p) {
        int temp = maxHeap.get(i);
        maxHeap.set(i, maxHeap.get(p));
        maxHeap.set(p, temp);
    }

    // 元素出堆
    int pop() {
        // 判空处理
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        // 交换根节点与最右叶节点（交换首元素与尾元素）
        swap(0, size() - 1);
        // 删除节点
        int val = maxHeap.remove(size() - 1);
        // 从顶至底堆化
        siftDown(0);
        // 返回堆顶元素
        return val;
    }

    // 判断堆是否为空
    boolean isEmpty() {
        return size() == 0;
    }

    // 从节点 i 开始，从顶至底堆化
    void siftDown(int i) {
        while (true) {
            // 判断节点 i、l、r 中值最大的节点，记为 max
            int l = left(i);
            int r = right(i);
            int max = i;
            if (l < size() && maxHeap.get(l) > maxHeap.get(max)) {
                max = l;
            }
            if (r < size() && maxHeap.get(r) > maxHeap.get(max)) {
                max = r;
            }
            // 若节点 i 最大或索引 l，r 越界，则无须继续堆化，跳出
            if (max == i) {
                break;
            }
            // 交换两节点
            swap(i, max);
            // 循环向下堆化
            i = max;
        }
    }

    /**
     * 构造方法 根据输入列表建堆，通过遍历堆化实现建堆
     * 时间复杂度 O(n)
     * @param nums 列表
     */
    MaxHeap(List<Integer> nums) {
        // 将列表元素原封不动添加进堆
        maxHeap = new ArrayList<>(nums);
        // 堆化除叶节点以外的其他所有节点
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }
    
    /**
     * 基于堆查找数组中最大的 k 个元素
     * 
     * 时间复杂度：O(nlogk)
     * @param nums  数组
     * @param k 最大元素个数
     * @return  包含最大的 k 个元素的堆
     */
    Queue<Integer> topKHeap(int[] nums, int k) {
        // 初始化小顶堆
        Queue<Integer> heap = new PriorityQueue<>();
        // 将数组的前 k 个元素入堆
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        // 从第 k + 1 个元素开始，保持堆的长度为 k
        for (int i = k; i < nums.length; i++) {
            // 若当前元素大于堆顶元素，则将堆顶元素出堆、当前元素入堆
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap;
    }
}
