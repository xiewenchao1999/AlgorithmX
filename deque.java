import java.util.Deque;
import java.util.LinkedList;

public class deque {
    public static void main(String[] args) {
        // 初始化双向队列
        Deque<Integer> deque = new LinkedList<>();

        // 元素入队
        deque.offerLast(2); // 添加至队尾
        deque.offerLast(5);
        deque.offerLast(4);
        deque.offerFirst(3);    // 添加至队首
        deque.offerFirst(1);

        // 访问元素
        int peekFirst = deque.peekFirst();  // 队首元素
        int peekLast = deque.peekLast();    // 队尾元素

        // 元素出队
        int popFirst = deque.pollFirst();   // 队首元素出队
        int popLast = deque.pollLast();     // 队尾元素出队

        // 获取双向队列的长度
        int size = deque.size();

        // 判断双向队列是否为空
        boolean isEmpty = deque.isEmpty();
    }
}

/**
 * 双向链表节点
 */
class LinkedListNode {
    int val;    // 节点值
    LinkedListNode next;    // 后继节点引用
    LinkedListNode prev;    // 前驱节点引用

    LinkedListNode(int val) {
        this.val = val;
        prev = next = null;
    }
}

/**
 * 基于双向链表实现的双向队列
 */
class LinkedListDeque {
    private LinkedListNode front, rear; // 头节点 front，尾节点 rear
    private int queueSize = 0;  // 双向队列的长度

    public LinkedListDeque () {
        front = rear = null;
    }

    /**
     * 获取双向队列的长度
     * @return  长度
     */
    public int size() {
        return queueSize;
    }

    /**
     * 判断双向队列是否为空
     * @return  bool
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 入队操作
     * @param num   入队元素
     * @param isFront   是否队首入队
     */
    private void push(int num, boolean isFront) {
        LinkedListNode node = new LinkedListNode(num);
        // 若链表为空，则令 front 和 rear 都指向node
        if (isEmpty()) {
            front = rear = node;
            // 队首入队操作
        } else if (isFront) {
            // 将 node 添加至链表头部
            front.prev = node;
            node.next = front;
            front = node;   // 更新头节点
            // 队尾入队操作
        } else {
            // 将 node 添加至链表尾部
            rear.next = node;
            node.prev = rear;
            rear = node;    // 更新尾节点
        }
        queueSize++;    // 更新队列长度
    }

    /**
     * 队首入队
     * @param num   元素
     */
    public void pushFirst(int num) {
        push(num, true);
    }

    /**
     * 队尾入队
     * @param num   元素
     */
    public void pushLast(int num) {
        push(num, false);
    }

    /**
     * 出队操作
     * @param isFront   是否是队首
     * @return  出队元素
     */
    private int pop(boolean isFront) {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        int val;
        // 队首出队操作
        if (isFront) {
            val = front.val;    // 暂存头节点值
            // 删除头节点值
            LinkedListNode fNext = front.next;
            if (fNext != null) {
                fNext.prev = null;
                front.next = null;
            }
            front = fNext;  // 更新头节点
            // 队尾出队操作
        } else {
            val = rear.val; // 暂存尾节点值
            // 删除尾节点
            LinkedListNode rPrev = rear.prev;
            if (rPrev != null) {
                rPrev.next = null;
                rear.prev = null;
            }
            rear = rPrev;   // 更新尾节点
        }
        queueSize--;    // 更新队列长度
        return val;
    }

    /**
     * 队首出队
     * @return  元素
     */
    public int popFirst() {
        return pop(true);
    }

    /**
     * 队尾出队
     * @return  元素
     */
    public int popLast() {
        return pop(false);
    }

    /**
     * 访问队首元素
     * @return  元素
     */
    public int peekFirst() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return front.val;
    }

    /**
     * 访问队尾元素
     * @return  元素
     */
    public int peekLast() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return rear.val;
    }

    /**
     * 返回数组
     * @return  array
     */
    public int[] toArray() {
        LinkedListNode node = front;
        int[] res = new int[size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }
}

/**
 * 基于环形数组实现的双向队列
 */
class ArrayDeque {
    private int[] nums; // 用于存储双向队列元素的数组
    private int front;  // 队首指针，指向队首元素
    private int queueSize;  // 双向队列长度

    /**
     * 构造方法
     * @param capacity  初始容量
     */
    public ArrayDeque (int capacity) {
        this.nums = new int[capacity];
        front = queueSize = 0;
    }

    /**
     * 获取双向队列的容量
     * @return  容量
     */
    public int capacity() {
        return nums.length;
    }

    /**
     * 获取双向队列的长度
     * @return  长度
     */
    public int size() {
        return queueSize;
    }

    /**
     * 判断双向队列是否为空
     * @return  bool
     */
    public boolean isEmpty() {
        return queueSize == 0;
    }

    /**
     * 计算环形数组索引
     * @param i 队列中下标
     * @return  实际索引
     */
    private int index(int i) {
        // 通过取余操作实现数组首尾相连
        // 当 i 越过数组尾部后，回到头部
        // 当 i 越过数组头部后，回到尾部
        return (i + capacity()) % capacity();
    }

    /**
     * 队首入队
     * @param num   元素
     */
    public void pushFirst(int num) {
        if (queueSize == capacity()) {
            System.out.println("双向队列已满");
            return;
        }
        // 队首指针向左移动一位
        // 通过取余操作实现 front 越过数组头部后回到尾部
        front = index(front - 1);
        // 将 num 添加至队首
        nums[front] = num;
        queueSize++;
    }

    /**
     * 队尾入队
     * @param num   元素
     */
    public void pushLast(int num) {
        if (queueSize == capacity()) {
            System.out.println("双向队列已满");
            return;
        }
        // 计算队尾指针，指向队尾索引 + 1
        int rear = index(front + queueSize);
        // 将 num 添加至队尾
        nums[rear] = num;
        queueSize++;
    }

    /**
     * 队首出队
     * @return  出队元素
     */
    public int popFirst() {
        int num = peekFirst();
        // 队首指针向后移动一位
        front = index(front + 1);
        queueSize--;
        return num;
    }

    /**
     * 访问队首元素
     * @return  队首元素
     */
    private int peekFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return nums[front];
    }

    /**
     * 队尾出队
     * @return  队尾元素
     */
    public int popLast() {
        int num = peekLast();
        queueSize--;
        return num;
    }

    /**
     * 访问队尾元素
     * @return  队尾元素
     */
    private int peekLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        // 计算尾元素索引
        int last = index(front + queueSize - 1);
        return nums[last];
    }

    /**
     * 返回数组
     * @return  array
     */
    public int[] toArray() {
        // 仅转换有效长度范围内的列表元素
        int[] res = new int[queueSize];
        for (int i = 0, j = front; i < queueSize; i++, j++) {
            res[i] = nums[index(j)];
        }
        return res;
    }
}