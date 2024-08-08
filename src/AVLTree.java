package src;

public class AVLTree {
    AVLTreeNode root;
    /**
     * 获取节点高度
     * @param node  节点
     * @return      height
     */
    int height(AVLTreeNode node) {
        return node == null ? -1 : node.height;
    }

    /**
     * 更新节点高度
     * @param node  节点
     */
    void updateHeight(AVLTreeNode node) {
        // 节点高度 = 最高子树高度 + 1
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 获取平衡因子
     * @param node  节点
     * @return  平衡因子 f   -1 <= f <= 1
     */
    int balanceFactor(AVLTreeNode node) {
        // 空节点的平衡因子为空
        if (node == null)
            return 0;
        // 节点平衡因子 = 节点左子树高度 - 节点右子树高度
        return height(node.left) - height(node.right);
    }

    /**
     * 对失衡节点处的子树进行右旋操作
     * @param node  失衡节点
     * @return  执行右旋操作后子树的根节点
     */
    AVLTreeNode rightRotate(AVLTreeNode node) {
        // 失衡节点的左子节点
        AVLTreeNode child = node.left;
        // child 节点的右子节点
        AVLTreeNode grandChild = child.right;
        // 以 child 为原点，将 node 向右旋转
        // 使 child 右节点替换为 node
        // child 原有的右节点 grandChild 连接到 node 的左节点
        child.right = node;
        node.left = grandChild;
        // 更新节点高度
        updateHeight(node);
        updateHeight(child);
        // 返回旋转后子树的根节点
        return child;
    }

    /**
     * 对失衡节点处的子树进行左旋操作
     * @param node  失衡节点
     * @return  执行左旋操作后子树的根节点
     */
    AVLTreeNode leftRotate(AVLTreeNode node) {
        // 失衡节点 node 的右子节点
        AVLTreeNode child = node.right;
        // child 节点的左子节点
        AVLTreeNode grandChild = child.left;
        // 以 child 为原点，将 node 向左旋转
        // 使 child 左子节点替换为 node
        // child 原有的左子节点 grandChild 连接到 node 的右子节点
        child.left = node;
        node.right = grandChild;
        // 更新节点高度
        updateHeight(node);
        updateHeight(child);
        // 返回旋转后子树的根节点
        return child;
    }

    /**
     * 执行旋转操作，使该子树重新恢复平衡
     * @param node  失衡节点
     * @return  子树根节点
     */
    AVLTreeNode rotate(AVLTreeNode node) {
        // 获取节点 node 的平衡因子
        int balanceFactor = balanceFactor(node);
        // 左偏树
        if (balanceFactor > 1) {
            // 如果左子树下其右子树高度大于其左子树高度
            if (balanceFactor(node.left) < 0) {
                // 左旋
                node.left = leftRotate(node.left);
            }
            // 右旋
            return rightRotate(node);
        }
        // 右偏树
        if (balanceFactor < -1) {
            // 如果右子树下其左子树高度大于其右子树高度
            if (balanceFactor(node.right) > 0) {
                // 右旋
                node.right = rightRotate(node.right);
            }
            // 左旋
            return leftRotate(node);
        }
        // 返回平衡树的根节点
        return node;
    }

    /**
     * 插入节点
     * @param val   元素
     */
    void insert(int val) {
        root = insertHelper(root, val);
    }

    /**
     * 递归插入节点（辅助方法）
     * @param node  节点
     * @param val   插入元素
     * @return  根节点
     */
    private AVLTreeNode insertHelper(AVLTreeNode node, int val) {
        if (node == null)
            return new AVLTreeNode(val);
        // 根据左小右大的规则，确定插入位置
        // 插入值小于该节点值，向节点左子节点下进行插入
        if (val < node.val)
            node.left = insertHelper(node.left, val);
        // 插入值大于该节点值，向节点右子节点下进行插入
        else if (val > node.val)
            node.right = insertHelper(node.right, val);
        // 插入值等于该节点值，重复，直接返回
        else
            return node;
        // 更新节点高度
        updateHeight(node);
        // 执行旋转操作，使该子树重新恢复平衡
        node = rotate(node);
        // 返回子树的根节点
        return node;
    }

    /**
     * 删除节点
     * @param val   节点值
     */
    void remove(int val) {
        root = removeHelper(root, val);
    }

    /**
     * 递归删除节点（辅助方法）
     * @param node  节点
     * @param val   删除节点值
     * @return  子树根节点
     */
    private AVLTreeNode removeHelper(AVLTreeNode node, int val) {
        if (node == null)
            return null;
        // 根据左小右大规则查找需要删除的节点
        // 当删除节点值小于节点值，进入左子节点删除
        if (val < node.val)
            node.left = removeHelper(node.left, val);
        // 当删除节点值大于节点值，进入右子节点删除
        else if (val > node.val)
            node.right = removeHelper(node.right, val);
        // 当删除节点值等于节点值
        else {
            // 当节点的子节点存在空节点
            if (node.left == null || node.right == null) {
                // 节点存在的子节点
                AVLTreeNode child = node.left != null ? node.left : node.right;
                // 子节点数量 = 0，直接删除 node 并返回
                if (child == null)
                    return null;
                // 子节点数量 = 1，直接删除 node
                else
                    node = child;
            } else {
                // 子节点数量 = 2
                // 将中序遍历的下个节点删除，并用该节点替换当前节点
                AVLTreeNode temp = node.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                node.right = removeHelper(node.right, val);
                node.val = temp.val;
            }
        }
        // 更新节点高度
        updateHeight(node);
        // 执行旋转操作，使该子树重新恢复平衡
        node = rotate(node);
        // 返回子树的根节点
        return node;
    }
}

/**
 * AVL 树节点类
 */
class AVLTreeNode {
    public int val;             // 节点值
    public int height;          // 节点高度
    public AVLTreeNode left;    // 左子节点
    public AVLTreeNode right;   // 右子节点
    public AVLTreeNode(int x) { val = x; }
}