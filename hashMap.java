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

/**
 * 链式地址哈希表
 */
class HashMapChaining {
    int size;   // 键值对数量
    int capacity;   // 哈希表容量
    double loadThres;   // 触发扩容的负载因子阈值
    int extendRatio;    // 扩容倍数
    List<List<Pair>> buckets;   // 桶数组

    /**
     * 构造方法
     */
    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThres = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    /**
     * 哈希函数
     * @param key   键值
     * @return  索引
     */
    int hashFunc(int key) {
        return key % capacity;
    }

    /**
     * 负载因子
     * @return  double
     */
    double loadFactor() {
        return (double) size / capacity;
    }

    /**
     * 查询操作
     * @param key   键值
     * @return  值
     */
    String get(int key) {
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);
        // 遍历桶，若找到 key，则返回对应 val
        for (Pair pair : bucket) {
            if (pair.key == key) {
                return pair.val;
            }
        }
        // 若未找到 key，则返回 null
        return null;
    }

    /**
     * 添加操作
     * @param key   键
     * @param val   值
     */
    void put(int key, String val) {
        // 当负载因子超过阈值，执行扩容
        if (loadFactor() > loadThres) {
            extend();
        }
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);
        // 遍历桶，若遇到指定 key，则更新对应 val 并返回
        for (Pair pair : bucket) {
            if (pair.key == key) {
                pair.val = val;
                return;
            }
        }
        // 若无该 key，则将键值对添加至尾部
        Pair pair = new Pair(index, val);
        bucket.add(pair);
        size++;
    }

    /**
     * 删除操作
     * @param key   键
     */
    void remove(int key) {
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);
        // 遍历桶，从中删除键值对
        for (Pair pair : bucket) {
            if (pair.key == key) {
                bucket.remove(pair);
                size--;
                break;
            }
        }
    }

    /**
     * 扩容哈希表
     */
    void extend() {
        // 暂存原哈希表
        List<List<Pair>> bucketsTemp = buckets;
        // 初始化扩容后的新哈希表
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
        // 将键值对从原哈希表搬运至新哈希表
        for (List<Pair> bucket : bucketsTemp) {
            for (Pair pair : bucket) {
                put(pair.key, pair.val);
            }
        }
    }

    /**
     * 打印哈希表
     */
    void print() {
        for (List<Pair> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + "->" + pair.val);
            }
            System.out.println(res);
        }
    }
}