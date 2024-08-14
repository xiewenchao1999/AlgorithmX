package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreorderTraversal {
    List<TreeNode> res = new ArrayList<>();

    /**
     * 给定一颗二叉树，搜索并记录所有值为 7 的节点，请返回节点列表
     * 前序遍历
     *
     * @param root 根节点
     */
    void preOrder1(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val == 7) {
            // 记录解
            res.add(root);
        }
        preOrder1(root.left);
        preOrder1(root.right);
    }

    List<List<TreeNode>> res2 = new ArrayList<>();
    List<TreeNode> path = new ArrayList<>();

    /**
     * 在二叉树中搜索所有值为 7 的节点，请返回根节点到这些节点的路径
     * 前序遍历
     *
     * @param root 根节点
     */
    void preOrder2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 尝试的搜索的路径
        path.add(root);
        if (root.val == 7) {
            // 记录解
            res2.add(new ArrayList<>(path));
        }
        preOrder2(root.left);
        preOrder2(root.right);
        // 回退
        path.remove(path.size() - 1);
    }

    List<List<TreeNode>> res3 = new ArrayList<>();
    List<TreeNode> path2 = new ArrayList<>();

    /**
     * 在二叉树中搜索所有值为 7 的节点，请返回根节点到这些节点的路径，并要求路径中不包含值为 3 的节点。
     * 前序遍历
     *
     * @param root 根节点
     */
    void preOrder3(TreeNode root) {
        // 剪枝：约束条件
        if (root == null || root.val == 3) {
            return;
        }
        // 尝试的搜索路径
        path2.add(root);
        if (root.val == 7) {
            // 记录解
            res3.add(new ArrayList<>(path2));
        }
        preOrder3(root.left);
        preOrder3(root.right);
        // 回退
        path2.remove(path2.size() - 1);
    }

    /*----------------回溯算法：尝试、回退、剪枝 >>> 框架代码-------------------*/

    /**
     * 判断当前状态是否为解
     *
     * @param state 状态
     * @return bool
     */
    boolean isSolution(List<TreeNode> state) {
        return !state.isEmpty() && state.get(state.size() - 1).val == 7;
    }

    /**
     * 记录解
     *
     * @param state 状态
     * @param res   结果列表
     */
    void recordSolution(List<TreeNode> state, List<List<TreeNode>> res) {
        res.add(new ArrayList<>(state));
    }

    /**
     * 判断在当前状态下，该选择是否合法
     *
     * @param state  状态
     * @param choice 选择
     * @return bool
     */
    boolean isValid(List<TreeNode> state, TreeNode choice) {
        return choice != null && choice.val != 3;
    }

    /**
     * 更新状态
     * @param state 状态
     * @param choice    选择
     */
    void makeChoice(List<TreeNode> state, TreeNode choice) {
        state.add(choice);
    }

    /**
     * 恢复状态
     * @param state 状态
     * @param choice    选择
     */
    void undoChoice(List<TreeNode> state, TreeNode choice) {
        state.remove(choice);
    }

    /**
     * 在二叉树中搜索所有值为 7 的节点，请返回根节点到这些节点的路径，并要求路径中不包含值为 3 的节点。
     * 回溯算法
     * @param state 状态：节点遍历路径
     * @param choices   选择：当前节点的左子节点和右子节点
     * @param res   结果：路径列表
     */
    void backtrack(List<TreeNode> state, List<TreeNode> choices, List<List<TreeNode>> res) {
        // 检查是否为解
        if (isSolution(state)) {
            // 记录解
            recordSolution(state, res);
        }
        // 遍历所有选择
        for (TreeNode choice : choices) {
            // 剪枝：检查选择是否合法
            if (isValid(state, choice)) {
                // 尝试：做出选择，更新状态
                makeChoice(state, choice);
                // 进行下一轮选择
                backtrack(state, Arrays.asList(choice.left, choice.right), res);
                // 回退：撤销选择，恢复到之前的状态
                undoChoice(state, choice);
            }
        }
    }
}
