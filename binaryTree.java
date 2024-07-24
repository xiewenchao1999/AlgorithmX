import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class binaryTree {
    public static void main(String[] args) {
        // 初始化节点
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        // 构建节点之间的引用
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        /*
        TreeNode P = new TreeNode(0);
        // 在 n1->n2 中间插入节点 P
        n1.left = P;
        P.left = n2;
        // 删除节点 P
        n1.left = n2;
        */

//        List<Integer> list = Traversal.levelOrder(n1);
        Traversal.preOrder(n1);
        System.out.println(Traversal.list);
    }


}

/**
 * 二叉树节点类
 */
class TreeNode {
    int val;    // 节点值
    TreeNode left;  // 左子节点引用
    TreeNode right; // 右子节点引用

    TreeNode(int x) {
        val = x;
    }
}

class Traversal {
    /**
     * 层序遍历-广度优先搜索 bfs
     *
     * @param root 根节点
     * @return 遍历列表
     */
    static List<Integer> levelOrder(TreeNode root) {
        // 初始化队列，加入根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 初始化一个列表，用于保存遍历序列
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();   // 队列出队
            list.add(node.val);             // 保存节点值
            if (node.left != null) {
                queue.offer(node.left);     // 左子节点入队
            }
            if (node.right != null) {
                queue.offer(node.right);    // 右子节点入队
            }
        }
        return list;
    }

    static List<Integer> list = new ArrayList<>();

    /**
     * 前序遍历-深度优先搜索 dfs-递归
     * @param root  根节点
     */
    static void preOrder(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：根节点->左子树->右子树
        list.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * @param root  根节点
     */
    static void inOrder(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树->根节点->右子树
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     * @param root  根节点
     */
    static void postOrder(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树->右子树->根节点
        postOrder(root.left);
        postOrder(root.right);
        list.add(root.val);
    }
}