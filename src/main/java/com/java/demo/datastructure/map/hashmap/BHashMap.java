package com.java.demo.datastructure.map.hashmap;

/**
 * <p>
 * 为什么HashMap不是线程安全的？ http://coolshell.cn/articles/9606.html
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/8/3
 * Time: 下午5:53
 */
public class BHashMap {

    //容量
    private int capacity;

    //默认容量
    private static final int defaultCapacity = 16;

    //自动扩容系数，为什么定义75%呢，经验值？
    private static final float loadFactor = 0.75f;

    //膨胀系数。每次扩容，扩多大呢？
    private static final int infateFactor = 2;

    //膨胀的水位值 ,一般为 容量 * 加载因子系数
    private int threshold;

    //实际放入Map中的数据个数，用于自动扩容使用
    private int size;


    private Entity[] data = {};

    //采用链表方式记录具体数据
    private class Entity {
        private Object key;
        private Object value;
        private Entity next;

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Entity getNext() {
            return next;
        }

        public void setNext(Entity next) {
            this.next = next;
        }
    }


    public BHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(" capacity illegal");
        }
        this.capacity = capacity;
        threshold = capacity;
        data = new Entity[capacity];
    }

    public BHashMap() {
        this(defaultCapacity);
    }


    public void put(Object key, Object value) {
        if (size > threshold) {
            //容器扩容
            inflateAndReHash();
        }
        int index = getHashIndex(key);
        Entity entity = new Entity();
        entity.setKey(key);
        entity.setValue(value);
        Entity oldEntity = data[index];
        if (oldEntity != null) {
            entity.setNext(oldEntity);
        }
        data[index] = entity;
        size++;
    }

    /**
     * 获取数据
     * UnThreadSafe
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        int keyIndex = getHashIndex(key);
        Entity entity = data[keyIndex];
        if (entity == null) {
            return null;
        }
        //如何从链表中获取数据？
        for (Entity e = entity; e != null; e = e.getNext()) {
            if (key != null && key == e.getKey() || key != null && key.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * 数据膨胀并做Rehash过程
     * UnThreadSafe
     */
    private void inflateAndReHash() {
        this.capacity = capacity * infateFactor;
        this.threshold = Float.valueOf(this.capacity * loadFactor).intValue();
        Entity[] tmp = new Entity[capacity];
        for (int i = 0; i < data.length; i++) {
            Entity entity = data[i];
            if (entity == null) {
                continue;
            }
            //后继节点的处理?
            while (entity != null) {
                Entity t = entity.getNext();
                int index = getHashIndex(entity.getKey());
                entity.next = tmp[index];
                tmp[index] = entity;
                entity = t;
            }
        }
        data = tmp;
    }

    /**
     * Hash算法
     *
     * @param key
     * @return
     */
    private int hash(Object key) {
        return Math.abs(key.hashCode());
    }

    /**
     * 计算Hash槽
     *
     * @param key
     * @return
     */
    private int getHashIndex(Object key) {
        return hash(key) % data.length;
    }

    public static void main(String[] args) {
        BHashMap map = new BHashMap();
        for (int i = 0; i < 20; i++) {
            map.put("hashMap" + i, "HashMap Demo " + i);
        }

        System.out.println(map.capacity);
        System.out.println(map.threshold);
        System.out.println(map.size);

        for (int i = 0; i < 20; i++) {
            System.out.println(i + "    " + map.get("hashMap" + i));
        }



    }

    /**
     * 问题：
     * 1. 虽然使用了链表的结构来处理Hash冲突，但是获取数据的时候不知道怎么从链表中获取 》改进：通过Key的相等性进行判断，hash一致、地址引用一致，值一致；
     * 2. 没有考虑相同元素Key重复加入的情况 》 改进： 在put的时候判断待加入元素key是否已经存在，如果存在，则覆盖，覆盖且让外界知晓；
     */
}
