package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class list {

    public static void main(String[] args) {
        // 无初始值
        List<Integer> list = new ArrayList<>();
        // 有初始值
        Integer[] numbers = {1, 3, 2, 5, 4};
        List<Integer> nums = new ArrayList<>(Arrays.asList(numbers));

        // 清空列表
        nums.clear();

        // 在尾部添加元素
        nums.add(1);
        nums.add(3);
        nums.add(2);
        nums.add(5);
        nums.add(4);

        // 在指定索引处插入元素
        nums.add(3, 6);

        // 删除指定索引处元素
        nums.remove(3);

        int count = 0;
        // 索引遍历列表
        for (int i = 0; i < nums.size(); i++) {
            count += nums.get(i);
        }
        // 直接遍历列表元素
        for (Integer num : nums) {
            count += num;
        }

        // 拼接两个列表
        List<Integer> nums2 = new ArrayList<>(Arrays.asList(new Integer[]{6, 8, 7, 10, 9}));
        nums.addAll(nums2);

        // 排序列表
        Collections.sort(nums);
    }
}

class MyList {
    private int[] arr;  // 数组（存储列表元素）
    private int capacity = 10;  // 列表容量（默认初始容量为 10）
    private int size = 0;   // 列表长度（当前元素数量）
    private int extendRatio = 2;    // 每次列表扩容的倍数

    /**
     * 构造函数
     */
    public MyList() {
        arr = new int[capacity];
    }

    /**
     * 获取列表长度（当前元素数量）
     * @return  size
     */
    public int size() {
        return size;
    }

    /**
     * 获取列表容量
     * @return  capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * 访问元素
     * @param index 索引
     * @return  元素
     */
    public int get(int index) {
        // 索引如果越界，则抛出异常
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return arr[index];
    }

    /**
     * 更新元素
     * @param index 指定索引
     * @param num   更新元素
     */
    public void set(int index, int num) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }

    /**
     * 在尾部添加元素
     * @param num   新增元素
     */
    public void add(int num) {
        // 元素数量超出容量时，触发扩容机制
        if (size == capacity) {
            extendCapacity();
        }
        arr[size] = num;
        // 更新元素数量
        size++;
    }

    /**
     * 在中间插入元素
     * @param index 插入位置索引
     * @param num   插入元素
     */
    public void insert(int index, int num) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        // 元素数量超出容量时，触发扩容机制
        if (size == capacity) {
            extendCapacity();
        }
        // 将索引 index 及其后元素都向后移动一位
        for (int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = num;
        // 更新元素数量
        size++;
    }

    /**
     * 删除元素
     * @param index 删除元素的索引
     * @return  被删除的元素
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        int num = arr[index];
        // 将索引 index 之后的元素都向前移动一位
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        // 更新元素数量
        size--;
        // 返回被删除的元素
        return num;
    }

    /**
     *  列表扩容
     */
    public void extendCapacity() {
        arr = Arrays.copyOf(arr, capacity * extendRatio);
        capacity = arr.length;
    }

    /**
     * 将列表转换为数组
     * @return  数组
     */
    public int[] toArray() {
        int size = size();
        // 仅转换有效长度范围内的列表元素
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = get(i);
        }
        return arr;
    }
}
