package com.java.demo.datastructure.map.hashmap;

/**
 * <p>
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/8/3
 * Time: 下午4:59
 */
public class AHashMap {

    private int capacity;
    private static final int defaultCapacity = 10;

    private Object[] data;

    public AHashMap() {
        this.capacity = defaultCapacity;
        data = new Object[capacity];
    }

    public AHashMap(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    public void put(Object key, Object value) {
        int keyIndex = hash(key);
        data[keyIndex] = value;
    }

    public Object get(Object key) {
        int keyIndex = hash(key);
        return data[keyIndex];
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public static void main(String[] args) {
        AHashMap map = new AHashMap();
        map.put("hash0", 1);
        map.put("hash11", 2);
        System.out.println(map.get("hash0")); //输出 2
        System.out.println(map.get("hash11")); //输出 2

        map.put(null, 3);
        System.out.println(map.get(null));
    }

    /**
     * 问题：
     * 1. 容量固定，随着数据的增多，Hash冲突的概率非常大； 》 改进：当元素容量达到一定程度后，自动调节容量
     * 2. Hash冲突后，采用的方式是覆盖，导致数据丢失；  》 改进：数据结构采用链表，冲突后，可以把冲突的数据节点挂载在链表中；
     * 3. 默认容量设置10，比较随意，为什么不是别的数字，或者2的指数 》 改进：设置为2的指数
     * 4. 没有考虑参数的取值范围：对null Key未进行处理 》 改进： 单独对null值进行处理
     * 5. 没有考虑数据类型，泛型
     * 6. 没有考虑递归方法
     * 7. 底层数据结构值保持了value，没有保持key的原始数据
     */
}
