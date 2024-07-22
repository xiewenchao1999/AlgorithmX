import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hashMap {
    public static void main(String[] args) {
        // 初始化哈希表
        Map<Integer, String> map = new HashMap<>();

        // 添加操作
        // 在哈希表中添加键值对 (key, value)
        map.put(12836, "哈哈");
        map.put(15937, "啰啰");
        map.put(16750, "算算");
        map.put(13276, "法法");
        map.put(10583, "丫丫");

        // 查询操作
        // 向哈希表中输入键 key，得到值 value
        String name = map.get(15937);

        // 删除操作
        // 在哈希表中删除键值对 (key, value)
        map.remove(10583);

        /*
        遍历哈希表
         */
        // 遍历键值对 key->value
        for (Map.Entry<Integer, String> kv : map.entrySet()) {
            System.out.printf("%d->%s%n", kv.getKey(), kv.getValue());
        }

        // 单独遍历键 key
        for (Integer key : map.keySet()) {
            System.out.println(key);
        }

        // 单独遍历值 value
        for (String val : map.values()) {
            System.out.println(val);
        }
    }
}

/**
 * 键值对
 */
class Pair {
    public int key;
    public String val;

    public Pair(int key, String val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * 基于数组实现的哈希表
 */
class ArrayHashMap {
    private List<Pair> buckets;

    /**
     * 构造函数
     */
    public ArrayHashMap() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            buckets.add(null);
        }
    }

    /**
     * 哈希函数
     * @param key   键
     * @return  转换后索引
     */
    private int hashFunc(int key) {
        return key % 100;
    }

    /**
     * 查询操作
     * @param key   键
     * @return  值
     */
    public String get(int key) {
        int index = hashFunc(key);
        Pair pair = buckets.get(index);
        if (pair == null) {
            return null;
        }
        return pair.val;
    }

    /**
     * 添加操作
     * @param key   键
     * @param val   值
     */
    public void put(int key, String val) {
        Pair pair = new Pair(key, val);
        int index = hashFunc(key);
        buckets.set(index, pair);
    }

    /**
     * 删除操作
     * @param key   键
     */
    public void remove(int key) {
        int index = hashFunc(key);
        // 置为 null，代表删除
        buckets.set(index, null);
    }

    /**
     * 获取所有键值对
     * @return  键值对列表
     */
    public List<Pair> pairSet() {
        List<Pair> pairSet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                pairSet.add(pair);
            }
        }
        return pairSet;
    }

    /**
     * 获取所有键
     * @return  键列表
     */
    public List<Integer> keySet() {
        List<Integer> keySet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                keySet.add(pair.key);
            }
        }
        return keySet;
    }

    /**
     * 获取所有值
     * @return  值列表
     */
    public List<String> valueSet() {
        List<String> valueSet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                valueSet.add(pair.val);
            }
        }
        return valueSet;
    }

    /**
     * 打印哈希表
     */
    public void print() {
        for (Pair kv : pairSet()) {
            System.out.println(kv.key + "->" + kv.val);
        }
    }
}

