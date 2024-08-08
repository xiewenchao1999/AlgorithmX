package src;

import java.util.LinkedList;
import java.util.Queue;

public class queue {
    public static void main(String[] args) {
        // 初始化队列
        Queue<Integer> queue = new LinkedList<>();

        // 元素入队
        queue.offer(1);
        queue.offer(3);
        queue.offer(2);
        queue.offer(5);
        queue.offer(4);

        // 元素出队
        int peek = queue.peek();
        // 元素入队
        int pop = queue.poll();
        // 获取队列的长度
        int size = queue.size();
        // 判断队列是否为空
        boolean isEmpty = queue.isEmpty();
    }
}

// 队列的链表实现
class LinkedListQueue {
    private ListNode front, rear;   // 头节点 front，尾节点 rear
    private int queueSize = 0;  // 队列初始长度

    /**
     * 队列初始化
     */
    public LinkedListQueue() {
        front = rear = null;
    }

    /**
     * 获取队列长度
     * @return  size
     */
    public int size() {
        return queueSize;
    }

    /**
     * 判断队列是否为空
     * @return  bool
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 入队
     * @param num   入队元素
     */
    public void push(int num) {
        // 在尾节点后添加 num
        ListNode node = new ListNode(num);
        // 如果队列为空，则令头、尾节点都指向该节点
        if (front == null) {
            front = node;
            rear = node;
            // 如果队列不为空，则将该节点添加到尾节点后
        } else {
            rear.next = node;
            rear = node;
        }
        queueSize++;
    }

    /**
     * 访问队首元素
     * @return  队首元素
     */
    public int peak() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return front.val;
    }

    /**
     * 出队
     * @return  出队元素
     */
    public int pop() {
        int num = peak();
        front = front.next;
        queueSize--;
        return num;
    }

    /**
     * 将链表转化为 Array 并返回
     * @return  Array
     */
    public int[] toArray() {
        ListNode node = front;
        int[] res = new int[size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }
}

/**
 * 基于环形数组实现的队列
 */
class ArrayQueue {
    private int[] nums; // 用于存储队列元素的数组
    private int front;  // 队首指针，指向队首元素
    private int queueSize;  // 队列长度

    /**
     * 初始化
     * @param capacity  容量大小
     */
    public ArrayQueue(int capacity) {
        nums = new int[capacity];
        front = queueSize = 0;
    }

    /**
     * 获取队列的容量
     * @return  容量
     */
    public int capacity() {
        return nums.length;
    }

    /**
     * 获取队列长度
     * @return  获取队列的长度
     */
    public int size() {
        return queueSize;
    }

    /**
     * 判断队列是否为空
     * @return  bool
     */
    public boolean isEmpty() {
        return queueSize == 0;
    }

    /**
     * 入队
     * @param num   入队元素
     */
    public void push(int num) {
        if (queueSize == capacity()) {
            System.out.println("队列已满");
            return;
        }
        // 计算新队尾指针，指向队尾索引+1
        // 通过取余操作实现rear尾节点越过数组尾部后回到头部
        int rear = (front + queueSize) % capacity();
        // 将 num 添加至队尾
        nums[rear] = num;
        queueSize++;
    }

    /**
     * 出队
     * @return  num
     */
    public int pop() {
        int num = peek();
        front = (front + 1) % capacity();
        queueSize--;
        return num;
    }

    /**
     * 访问队首元素
     * @return  num
     */
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return nums[front];
    }

    /**
     * 将队列转为数组
     * @return  Array
     */
    public int[] toArray() {
        // 仅转换有效长度范围内的列表元素
        int[] res = new int[queueSize];
        for (int i = 0, j = front; i < queueSize; i++, j++) {
            res[i] = nums[j % capacity()];
        }
        return res;
    }
}

