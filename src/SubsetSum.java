package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集和问题
 */
public class SubsetSum {
    /**
     * 给定一个正整数数组 nums 和一个目标正整数 target ，
     * 请找出所有可能的组合，使得组合中的元素和等于 target 。
     * 给定数组无重复元素，每个元素可以被选取多次。
     * 请以列表形式返回这些组合，列表中不应包含重复组合
     * 回溯算法：子集和 I（包含重复元素）
     *
     * @param state   状态：节点遍历路径
     * @param target  目标值
     * @param total   子集和：元素和
     * @param choices 选择：节点的全部子节点
     * @param res     结果：子集结果
     */
    void backtrackNative(List<Integer> state, int target, int total, int[] choices, List<List<Integer>> res) {
        // 子集和等于 target 时，记录解
        if (total == target) {
            res.add(new ArrayList<>(state));
            return;
        }
        // 遍历所有选择
        for (int i = 0; i < choices.length; i++) {
            // 剪枝：若子集和超过 target，则跳过该选择
            if (total + choices[i] == target) {
                continue;
            }
            // 尝试：做出选择，更新元素和 total
            state.add(choices[i]);
            // 进行下一轮选择
            backtrackNative(state, target, total, choices, res);
            // 回退：撤销选择，恢复到之前的状态
            state.remove(state.size() - 1);
        }
    }

    /**
     * 求解子集和 I （包含重复子集）
     * @param nums      数组
     * @param target    目标值
     * @return          结果集
     */
    List<List<Integer>> subsetSum(int[] nums, int target) {
        // 状态（子集）
        List<Integer> state = new ArrayList<>();
        // 子集和
        int total = 0;
        // 结果列表（子集列表）
        List<List<Integer>> res = new ArrayList<>();
        backtrackNative(state, target, total, nums, res);
        return  res;
    }

    /**
     * 回溯算法：子集和 I
     * @param state     状态：节点遍历路径
     * @param target    目标值
     * @param choices   选择
     * @param start     遍历起始点
     * @param res       结果：子集
     */
    void backtrackI(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        // 子集和等于 target 时，记录解
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }

        // 遍历所有选择
        // 剪枝2：从 start 开始遍历，避免生成重复子集
        for (int i = start; i < choices.length; i++) {
            // 剪枝1：若子集和超过 target，则直接结束循环
            // 因为数组已排序，后边的元素更大，子集和一定超过 target
            if (target - choices[i] < 0) {
                break;
            }
            // 尝试：做出选择，更新 target，start
            state.add(choices[i]);
            // 进行下一轮选择
            backtrackI(state, target - choices[i], choices, i, res);
            // 回退：撤销选择，恢复到之前的状态
            state.remove(state.size() - 1);
        }
    }

    /**
     * 求解子集和 I
     * @param nums      数组
     * @param target    目标值
     * @return  子集结果
     */
    List<List<Integer>> subsetSumI(int[] nums, int target) {
        // 状态（子集）
        List<Integer> state = new ArrayList<>();
        // 对 nums 进行排序
        Arrays.sort(nums);
        // 遍历起始点
        int start = 0;
        // 结果列表（子集列表）
        List<List<Integer>> res = new ArrayList<>();
        backtrackI(state, target, nums, start, res);
        return res;
    }


    /**
     * 给定一个正整数数组 nums 和一个目标正整数 target ，
     * 请找出所有可能的组合，使得组合中的元素和等于 target 。
     * 给定数组可能包含重复元素，每个元素只可被选择一次。
     * 请以列表形式返回这些组合，列表中不应包含重复组合。
     * 回溯算法：子集和 II
     * @param state     状态：节点遍历路径
     * @param target    目标值
     * @param choices   选择
     * @param start     遍历起始点
     * @param res       结果：子集
     */
    void backtrackII(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        // 子集和等于 target 时，记录解
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        // 遍历所有选择
        // 剪枝2：从 start 开始遍历，避免生成重复子集
        // 剪枝3：从 start 开始遍历，避免重复选择统一元素
        for (int i = start; i < choices.length; i++) {
            // 剪枝1：若子集和超过 target，则直接结束循环
            // 因为数组已排序，后边元素更大，子集和一定超过 target
            if (target - choices[i] < 0) {
                break;
            }
            // 剪枝4：如果该元素与左边元素相等，说明该搜索分支重复，直接跳过
            if (i > start && choices[i] == choices[i - 1]) {
                continue;
            }
            // 尝试：做出选择，更新 target，start
            state.add(choices[i]);
            // 进行下一轮选择
            backtrackII(state, target - choices[i], choices, i + 1, res);
            // 回退：撤销选择，恢复到之前的状态
            state.remove(state.size() - 1);
        }
    }

    /**
     * 求解子集和 II
     * @param nums      数组
     * @param target    目标值
     * @return  子集结果
     */
    List<List<Integer>> subsetSumII(int[] nums, int target) {
        // 状态（子集）
        List<Integer> state = new ArrayList<>();
        // 对 nums 进行排序
        Arrays.sort(nums);
        // 遍历起始点
        int start = 0;
        // 结果列表（子集列表）
        List<List<Integer>> res = new ArrayList<>();
        backtrackII(state, target, nums, start, res);
        return res;
    }
}
