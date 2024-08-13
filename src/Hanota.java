package src;

import java.util.List;

/**
 * 给定三根柱子，记为 A、B 和 C 。起始状态下，柱子 A 上套着
 *  个圆盘，它们从上到下按照从小到大的顺序排列。我们的任务是要把这
 *  个圆盘移到柱子 C 上，并保持它们的原有顺序不变。在移动圆盘的过程中，需要遵守以下规则。
 *
 * 圆盘只能从一根柱子顶部拿出，从另一根柱子顶部放入。
 * 每次只能移动一个圆盘。
 * 小圆盘必须时刻位于大圆盘之上。
 */
public class Hanota {
    /**
     * 移动一个圆盘
     * @param src   源柱
     * @param tar   目标柱
     */
    void move(List<Integer> src, List<Integer> tar) {
        // 从 src 顶部拿出一个圆盘
        Integer pan = src.remove(src.size() - 1);
        // 将圆盘放入 tar 顶部
        tar.add(pan);
    }

    /**
     * 求解汉诺塔问题
     * @param i     圆盘数
     * @param src   源柱
     * @param buf   缓冲柱
     * @param tar   目标柱
     */
    void dfs(int i, List<Integer> src, List<Integer> buf, List<Integer> tar) {
        // 若 src 只剩下一个圆盘，则直接将其移到 tar
        if (i == 1) {
            move(src, tar);
            return;
        }
        // 子问题 f(i-1)：将 src 顶部 i-1 个圆盘借助 tar 移到 buf
        dfs(i - 1, src, tar, buf );
        // 子问题 f(1)：将 src 剩余一个圆盘移动到 tar
        move(src, tar);
        // 子问题 f(i-1)：将 buf 顶部 i-1 个圆盘借助 src 移到 tar
        dfs(i - 1, buf, src, tar);
    }

    /**
     * 求解汉诺塔问题
     * @param A 源柱
     * @param B 缓冲柱
     * @param C 目标柱
     */
    void solveHanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        // 将 A 顶部 n 个圆盘借助 B 移到 C
        dfs(n, A, B, C);
    }
}
