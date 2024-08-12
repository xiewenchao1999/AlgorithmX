package src;

import java.util.HashMap;
import java.util.Map;

/**
 * 构建二叉树问题
 * 给定一棵二叉树的前序遍历 preorder 和中序遍历 inorder ，
 * 请从中构建二叉树，返回二叉树的根节点。
 * 假设二叉树中没有值重复的节点
 */
public class BuildTree {
    /**
     * 构建二叉树：分治
     * @param preorder      前序遍历
     * @param inorderMap    中序遍历
     * @param i             当前树索引
     * @param l             左子树索引
     * @param r             右子树索引
     * @return              返回根节点
     */
    TreeNode dfs(int[] preorder, Map<Integer, Integer> inorderMap, int i, int l, int r) {
        // 子树区间为空时终止
        if (r - l < 0)
            return null;
        // 初始化根节点
        TreeNode root = new TreeNode(preorder[i]);
        // 查询 m，从而划分左右子树
        int m = inorderMap.get(preorder[i]);
        // 子问题：构建左子树
        root.left = dfs(preorder, inorderMap, i + 1, l, m - 1);
        // 子问题：构建右子树
        root.right = dfs(preorder, inorderMap, i + 1 + m - l, m + 1, r);
        // 返回根节点
        return root;
    }

    /**
     * 构建二叉树
     * @param preorder  前序遍历
     * @param inorder   中序遍历
     * @return          根节点
     */
    TreeNode buildTree(int[] preorder, int[] inorder) {
        // 初始化哈希表，存储 inorder 元素到索引的映射
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = dfs(preorder, inorderMap, 0, 0, preorder.length - 1);
        return root;
    }
}
