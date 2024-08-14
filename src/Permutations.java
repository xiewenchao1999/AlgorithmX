package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 全排列问题
 */
public class Permutations {
    /**
     * 输入一个整数数组，其中不包含重复元素，返回所有可能的排列。
     * 回溯算法I：全排列
     * @param state     状态：节点遍历路径
     * @param choices   选择：当前节点的子节点
     * @param selected  已选择：记录子节点是否已选择
     * @param res   结果：规定排列的结果
     */
    void backtrackI(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // 当状态长度等于元素数量时，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<Integer>(state));
            return;
        }

        // 遍历所有选择
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            // 剪枝：不允许重复选择元素
            if (!selected[i]) {
                // 尝试：做出选择，更新状态
                selected[i] = true;
                state.add(choice);
                // 进行下一轮选择
                backtrackI(state, choices, selected, res);
                // 回退：撤销选择，恢复到之前的状态
                selected[i] = false;
                state.remove(state.size() - 1);
            }
        }
    }

    /**
     * 输入一个整数数组，其中不包含重复元素，返回所有可能的排列。
     * 全排列 I
     * @param nums 数组
     * @return  排列结果
     */
    List<List<Integer>> permutationsI(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackI(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }


    /**
     * 输入一个整数数组，数组中可能包含重复元素，返回所有不重复的排列。
     * 回溯算法：全排列II
     * @param state     状态：节点遍历路径
     * @param choices   选择：当前节点的子节点
     * @param selected  已选择：记录子节点是否已选择
     * @param res   结果：规定排列的结果
     */
    void backtrackII(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // 当状态长度等于元素数量时，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<>(state));
            return;
        }

        // 遍历所有选择
        Set<Integer> duplicated = new HashSet<>();
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            // 剪枝：不允许重复选择元素，且不允许重复选择相等元素
            if (!selected[i] && !duplicated.contains(choice)) {
                // 尝试：做出选择，更新状态
                duplicated.add(choice);
                selected[i] = true;
                state.add(choice);
                // 进行下一轮选择
                backtrackII(state, choices, selected, res);
                // 回退：撤销选择，恢复到之间的状态
                selected[i] = false;
                state.remove(state.size() - 1);
            }
        }
    }

    /**
     * 输入一个整数数组，数组中可能包含重复元素，返回所有不重复的排列。
     * 全排列 II
     * @param nums  数组
     * @return  排列结果
     */
    List<List<Integer>> permutationsII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackII(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }
}
