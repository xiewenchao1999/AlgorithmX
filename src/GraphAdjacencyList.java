package src;

import java.util.*;

/**
 * 基于邻接表实现的无向图
 */
public class GraphAdjacencyList {
    // 邻接表
    // key：顶点，value：该顶点的所有邻接顶点
    Map<Vertex, List<Vertex>> adjacencyList;

    /**
     * 构造方法
     * @param edges 边
     */
    public GraphAdjacencyList(Vertex[][] edges) {
        this.adjacencyList = new HashMap<>();

        // 添加所有顶点和边
        for (Vertex[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    /**
     * 获取顶点数量
     * @return int
     */
    public int size() {
        return adjacencyList.size();
    }

    /**
     * 添加边
     * @param vet1 边的一个顶点
     * @param vet2 边的另一个顶点
     */
    public void addEdge(Vertex vet1, Vertex vet2) {
        if (!adjacencyList.containsKey(vet1) || !adjacencyList.containsKey(vet2) || vet1 == vet2) {
            throw new IllegalArgumentException();
        }
        // 添加边，向两个顶点中分别添加对顶点
        adjacencyList.get(vet1).add(vet2);
        adjacencyList.get(vet2).add(vet1);
    }

    /**
     * 删除边
     * @param vet1 边的一个顶点
     * @param vet2 边的另一个顶点
     */
    public void removeEdge(Vertex vet1, Vertex vet2) {
        if (!adjacencyList.containsKey(vet1) || !adjacencyList.containsKey(vet2) || vet1 == vet2) {
            throw new IllegalArgumentException();
        }
        // 删除边
        adjacencyList.get(vet1).remove(vet2);
        adjacencyList.get(vet2).remove(vet1);
    }

    /**
     * 添加顶点
     * @param vet 顶点
     */
    public void addVertex(Vertex vet) {
        if (adjacencyList.containsKey(vet)) {
            return;
        }
        // 在邻接表中添加一个新链表
        adjacencyList.put(vet, new ArrayList<>());
    }

    /**
     * 删除顶点
     * @param vet 顶点
     */
    public void removeVertex(Vertex vet) {
        if (!adjacencyList.containsKey(vet)) {
            throw new IllegalArgumentException();
        }
        // 在邻接表中删除顶点 vet 对应的链表
        adjacencyList.remove(vet);
        // 遍历其他顶点的链表，删除所有包含 vet 的边
        for (List<Vertex> list : adjacencyList.values()) {
            list.remove(vet);
        }
    }

    /**
     * 打印邻接表
     */
    public void print() {
        System.out.println("邻接表 = ");
        for (Map.Entry<Vertex, List<Vertex>> pair : adjacencyList.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex vertex : pair.getValue()) {
                tmp.add(vertex.val);
            }
            System.out.println(pair.getKey().val + ": " + tmp + ",");
        }
    }
}

