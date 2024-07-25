/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    /**
     * 查找节点
     *
     * @param num  值
     * @param root 根节点
     * @return 对应节点
     */
    TreeNode search(int num, TreeNode root) {
        TreeNode cur = root;
        // 循环查找，越过叶节点后跳出
        while (cur != null) {
            // 目标节点在 cur 的右子树
            if (cur.val < num)
                cur = cur.right;
                // 目标节点在 cur 的左子树
            else if (cur.val > num) {
                cur = cur.left;
                // 找到目标节点，跳出循环
            } else {
                break;
            }
        }
        // 返回目标节点
        return cur;
    }

    /**
     * 插入节点
     * @param num   值
     * @param root  根节点
     */
    void insert(int num, TreeNode root) {
        // 若树为空，则初始化根节点
        if (root == null) {
            root = new TreeNode(num);
            return;
        }
        TreeNode cur = root, pre = null;
        // 循环查找，越过叶节点后跳出
        while (cur != null) {
            // 找到重复节点，直接返回
            if (cur.val == num)
                return;
            pre = cur;
            // 插入位置在 cur 的右子树中
            if (cur.val < num) {
                cur = cur.right;
                // 插入位置在 cur 的左子树中
            } else {
                cur = cur.left;
            }
        }
        // 插入节点
        TreeNode node = new TreeNode(num);
        if (pre.val < num)
            pre.right = node;
        else
            pre.left = node;
    }
}
