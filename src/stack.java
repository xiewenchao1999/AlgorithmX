package src;

import java.util.ArrayList;
import java.util.Stack;

public class stack {
    public static void main(String[] args) {
        // 初始化栈
        Stack<Integer> stack = new Stack<>();

        // 元素入栈
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(5);
        stack.push(4);

        // 访问栈顶元素
        int top = stack.peek();

        // 元素出栈
        int pop = stack.pop();

        // 获取栈的长度
        int size = stack.size();

        // 判断栈是否为空
        boolean isEmpty = stack.isEmpty();
    }
}

/**
 * 基于链表实现栈
 */
class LinkedListStack {
    private ListNode stackPeek; // 将头节点作为栈顶
    private int stackSize = 0;  // 栈的长度

    /**
     * 栈初始化
     */
    public LinkedListStack() {
        stackPeek = null;
    }

    /**
     * 获取栈的长度
     *
     * @return 栈长度
     */
    public int size() {
        return stackSize;
    }

    /**
     * 判断栈是否为空
     *
     * @return bool
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 入栈
     *
     * @param num 入栈元素
     */
    public void push(int num) {
        ListNode node = new ListNode(num);
        // 入栈元素插入链表头节点
        node.next = stackPeek;
        stackPeek = node;
        stackSize++;
    }

    /**
     * 访问栈顶元素
     *
     * @return 栈顶元素
     */
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stackPeek.val;
    }

    /**
     * 出栈
     *
     * @return 出栈元素
     */
    public int pop() {
        int num = peek();
        // 出栈删除头节点
        stackPeek = stackPeek.next;
        stackSize--;
        return num;
    }

    /**
     * 将链表转为 Array 并返回
     *
     * @return Array
     */
    public int[] toArray() {
        ListNode node = stackPeek;
        int[] res = new int[size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }
}

/**
 * 基于动态数组实现栈
 */
class ArrayStack {
    private ArrayList<Integer> stack;

    /**
     * 初始化
     */
    public ArrayStack() {
        stack = new ArrayList<>();
    }

    /**
     * 获取栈的长度
     * @return  size
     */
    public int size() {
        return stack.size();
    }

    /**
     * 判断栈是否为空
     * @return  bool
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 入栈
     * @param num   入栈元素
     */
    public void push(int num) {
        stack.add(num);
    }

    /**
     * 出栈
     * @return  出栈元素
     */
    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stack.remove(size() - 1);
    }

    /**
     * 访问栈顶元素
     * @return  栈顶元素
     */
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stack.get(size() - 1);
    }

    /**
     * 将栈转换为 Array 并返回
     * @return  Array
     */
    public Object[] toArray() {
        return stack.toArray();
    }
}