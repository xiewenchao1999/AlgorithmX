import java.util.*;

/**
 * 图遍历
 */
public class GraphTraversal {
    /**
     * 广度优先遍历
     * 使用邻接表表示图，以便获取指定顶点的所有邻接顶点
     * @param graph 图
     * @param startVet 起始顶点
     * @return 顶点列表
     */
    List<Vertex> graphBFS(GraphAdjacencyList graph, Vertex startVet) {
        // 顶点遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希集合，用于记录已被访问过的顶点
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVet);
        // 队列，用于实现 BFS
        Queue<Vertex> que = new LinkedList<>();
        que.offer(startVet);

        // 以顶点 v 为起点，循环直至访问完所有顶点
        while (!que.isEmpty()) {
            // 队首顶点出队
            Vertex v = que.poll();
            // 记录访问顶点
            res.add(v);
            // 遍历该顶点的所有邻接顶点
            for (Vertex vertex : graph.adjacencyList.get(v)) {
                // 跳过已被访问的顶点
                if (visited.contains(vertex)) {
                    continue;
                }
                // 将未被访问的顶点入队
                que.offer(vertex);
                // 标记该顶点已被访问
                visited.add(vertex);
            }
        }
        // 返回顶点遍历序列
        return res;
    }

    /**
     * 深度优先遍历辅助函数
     * @param graph 以邻接表来表示的图
     * @param visited   哈希集合，记录已被访问过的顶点
     * @param res   列表，记录访问顶点
     * @param vet   正在访问的顶点
     */
    void dfs(GraphAdjacencyList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        // 记录访问顶点
        res.add(vet);
        // 标记该顶点已被访问
        visited.add(vet);
        // 遍历该顶点的所有邻接顶点
        for (Vertex adjVet : graph.adjacencyList.get(vet)) {
            if (visited.contains(adjVet)) {
                continue;
            }
            // 递归访问邻接顶点
            dfs(graph, visited, res, adjVet);
        }
    }

    /**
     * 深度优先遍历
     * 使用邻接表来表示图，以便获取指定顶点的所有邻接顶点
     * @param graph 以临界别来表示的图
     * @param startVet  起始顶点
     * @return  顶点列表
     */
    List<Vertex> graphDFS(GraphAdjacencyList graph, Vertex startVet) {
        // 顶点遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希集合，用于记录已被访问过的顶点
        Set<Vertex> visited = new HashSet<>();
        dfs(graph, visited, res, startVet);
        return res;
    }
}
