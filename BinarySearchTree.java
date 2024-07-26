/**
 * 二叉搜索树
 */
public class BinarySearchTree extends binaryTree {
    TreeNode root;

    /**
     * 查找节点
     *
     * @param num  值
     * @return 对应节点
     */
    TreeNode search(int num) {
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
     *
     * @param num  值
     */
    void insert(int num) {
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

    /**
     * 删除节点
     * @param num   目标元素
     */
    void remove(int num) {
        // 若树为空，直接返回
        if (root == null)
            return;

        TreeNode cur = root, pre = null;

        // 循环查找，越过叶节点后跳出
        while (cur != null) {
            // 找到待删除节点，跳出循环
            if (cur.val == num)
                break;

            // 记录待删除节点的父节点
            pre = cur;

            // 待删除节点在 cur 的右子树中
            if (cur.val < num)
                cur = cur.right;
            // 待删除节点在 cur 的左子树中
            else
                cur = cur.left;
        }

        // 若未找到待删除节点，则直接返回
        if (cur == null)
            return;
        // 若找到待删除节点
        // 当待删除结点的子节点数 = 0 or 1
        if (cur.left == null || cur.right == null) {
            // 获取待删除节点的子节点 child
            TreeNode child = cur.left != null ? cur.left : cur.right;

            // 删除节点
            // 当待删除节点非根节点
            if (cur != root) {
                // 删除操作：使子节点替换待删除节点
                if (pre.left == cur)
                    pre.left = child;
                else
                    pre.right = child;
            } else {
                // 当待删除节点为根节点，则重新指定根节点
                root = child;
            }
            // 当待删除节点的子节点数 = 2
        } else {
            // 获取中序遍历中 cur 的下一个节点
            // 以保证二叉搜索树左小右大的存储规律
            TreeNode tmp = cur.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            // 递归删除节点 tmp
            remove(tmp.val);
            // 用 tmp 替换 cur
            cur.val = tmp.val;
        }
    }
}
