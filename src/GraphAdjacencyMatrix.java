package src;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于邻接矩阵实现的无向图类
 */
public class GraphAdjacencyMatrix {
    List<Integer> vertices; // 顶点列表，元素代表“顶点值”，索引代表“顶点索引”
    List<List<Integer>> adjacencyMatrix;    // 邻接矩阵，行列索引对应“顶点索引”

    /**
     * 构造方法
     * @param vertices  顶点列表
     * @param edges 顶点索引（对应 vertices 元素索引）
     */
    public GraphAdjacencyMatrix(int[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = new ArrayList<>();

        // 添加顶点
        for (int val : vertices) {
            addVertex(val);
        }

        // 添加边
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
        }
        
    }

    /**
     * 获取顶点数量
     * @return int
     */
    public int size() {
        return vertices.size();
    }

    /**
     * 添加顶点
     * @param val 顶点值
     */
    public void addVertex(int val) {
        int n = size();

        // 向顶点列表中添加新顶点的值
        vertices.add(val);

        // 在邻接矩阵中添加一行，并用 0 补齐新行
        List<Integer> newRow = new ArrayList<>(n);
        for (int j = 0; j < n; j++) {
            newRow.add(0);
        }
        adjacencyMatrix.add(newRow);
        // 在邻接矩阵中添加一列，并用 0 补齐新列
        for (List<Integer> row : adjacencyMatrix) {
            row.add(0);
        }
    }

    /**
     * 删除顶点
     * @param index 顶点索引
     */
    public void removeVertex(int index) {
        // 索引越界与相等时的处理
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        // 在顶点列表中移除索引 index 的顶点
        vertices.remove(index);
        // 在邻接矩阵中删除索引 index 的行
        adjacencyMatrix.remove(index);
        // 在邻接矩阵中删除索引 index 的列
        for (List<Integer> row : adjacencyMatrix) {
            row.remove(index);
        }
    }

    /**
     * 添加边
     * @param i 元素索引
     * @param j 元素索引
     */
    public void addEdge(int i, int j) {
        // 索引越界与相等时的处理
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException();
        }
        // 在无向图中，邻接矩阵关于主对角线对称，即满足 (i, j) == (j, i)
        adjacencyMatrix.get(i).set(j, 1);
        adjacencyMatrix.get(j).set(i, 1);
    }

    /**
     * 删除边
     * @param i 元素索引
     * @param j 元素索引
     */
    public void removeEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException();
        }
        adjacencyMatrix.get(i).set(j, 0);
        adjacencyMatrix.get(j).set(i, 0);
    }

    /**
     * 打印邻接矩阵
     */
    public void print() {
        System.out.println("顶点列表 = ");
        System.out.println(vertices);
        System.out.println("邻接矩阵 = ");
        System.out.println(adjacencyMatrix);
    }
}
