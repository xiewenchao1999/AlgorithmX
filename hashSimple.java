public class hashSimple {
    private final int MODULUS = 1000000007;

    /**
     * 加法哈希
     * @param key   键
     * @return  哈希值
     */
    int addHash(String key) {
        long hash = 0;
        for (char c : key.toCharArray()) {
            // 对大质数取模，可以最大化保证哈希值的均匀分布
            hash = (hash + (int) c) % MODULUS;
        }
        return (int) hash;
    }

    /**
     * 乘法哈希
     * @param key   键
     * @return  哈希值
     */
    int mulHash(String key) {
        long hash = 0;
        for (char c : key.toCharArray()) {
            hash = (31 * hash + (int) c) % MODULUS;
        }
        return (int) hash;
    }

    /**
     * 异或哈希
     * @param key   键
     * @return  哈希值
     */
    int xorHash(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash ^= (int) c;
        }
        return hash & MODULUS;
    }

    /**
     * 旋转哈希
     * @param key   键
     * @return  哈希值
     */
    int rotHash(String key) {
        long hash = 0;
        for (char c : key.toCharArray()) {
            hash = ((hash << 4) ^ (hash >> 28) ^ (int) c) % MODULUS;
        }
        return (int) hash;
    }
}
