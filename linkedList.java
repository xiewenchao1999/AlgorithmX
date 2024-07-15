class ListNode {
    int val;    // 节点值
    ListNode next;  // 指向下一节点的引用

    // 构造函数
    ListNode(int x) {
        val = x;
    }
}

public class linkedList {
    ListNode head;

    public linkedList() {

        ListNode n0 = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(5);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        head = n0;
    }

    /**
     * 在链表的节点 n0 之后插入节点 P
     * @param n0    目标节点
     * @param P 插入节点
     */
    void insert(ListNode n0, ListNode P) {
        P.next = n0.next;
        n0.next = P;
    }

    /**
     * 删除链表的节点 n0 之后的首个节点
     * @param n0    目标节点
     */
    void remove(ListNode n0) {
        if (n0.next == null) {
            return;
        }
        ListNode P = n0.next;
        n0.next = P.next;
    }

    /**
     * 访问链表中索引为index的节点
     * @param head  链表头节点
     * @param index 访问节点索引
     * @return  访问节点
     */
    ListNode access(ListNode head, int index) {
        for (int i = 0; i < index; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }
        return head;
    }

    /**
     * 在链表中查找值为target的首个节点
     * @param head  头节点
     * @param target    目标值
     * @return  节点索引
     */
    int find(ListNode head, int target) {
        int index = 0;
        while (head != null) {
            if (head.val == target) {
                return index;
            }
            head = head.next;
            index++;
        }
        return -1;
    }
}
