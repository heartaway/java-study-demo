package com.java.demo.collection;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义数组列表
 * 自己编写的列表的不足点：
 * 1. 没有考虑集合的序列化；
 * 2. ArrayList自主实现了序列化方式，而且数据元素是transit,为什么不采用通用化的序列化方式呢？
 * 3. 一些参数校验不完善，比如capacity；
 * 4. 我定义的数组默认情况下就占用了16个元素，造成了一定的资源浪费；
 * 5. 我设定的数组的最大为 Integer.MAX_VALUE ，这个大小有可能超过JVM的最大大小，而导致OOM，原因是一些JVM的会在数组中预留一些头部数据
 * 6. 在数据拷贝中采用了慢复制，没有采用System.arrayCopy进行快复制方法，性能上不能保障；
 * 7. 在进行数组扩容的时候，我设置的最小阀值为数组操作最大容量的一半时就进行扩容，但是ArrayList只有当数组长度接近容量的时候才进行扩容，内存利用更高效；
 * 8. ArrayList记录了modCount，用于处理单请求混合操作时的异常，比如循环读时的写操作；ArrayList本身就是非线程安全的；
 * CopyOnWriteArrayList 是ArrayList的一个线程安全的实现方式，实现就是读写分离；
 * <p>
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/7/6
 * Time: 下午8:04
 */
public class CustomArrayList<T> {

    private static final Object[] EMPTY_ELEMENT = {}; //{}简洁的数组定义方式，必须与变量同时使用

    private Object[] elements;

    private int size = 0;

    private int capacity = 16;

    private int MAX_CAPACITY = Integer.MAX_VALUE;

    public int size() {
        return size;
    }

    public CustomArrayList() {
        elements = new Object[capacity];
    }

    public CustomArrayList(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
            elements = new Object[capacity];
        } else if (capacity == 0) {
            this.capacity = capacity;
            elements = EMPTY_ELEMENT;
        } else {
            throw new IllegalArgumentException("Illegal Capacity : " + capacity);
        }
    }

    public boolean isEmpty() {
        if (size > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean add(T e) {
        autoScaleCapacity();
        if (size == capacity) {
            throw new IllegalAccessError("capacity is full ,cannot add any element");
        } else {
            elements[size] = e;
            size++;
        }

        return true;
    }

    public boolean remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("argument is not allow less than zero");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("");
        }

        for (int i = index; i < size; i++) {
            if (i + 1 < size) {
                elements[index] = elements[i + 1];
            } else {
                elements[index] = null;
            }
            index++;
        }

        size--;

        return true;
    }

    public boolean containe(T e) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (e == null && elements[i] == null) {
                    return true;
                }
                if (elements[i] != null && elements[i].equals(e)) {
                    //如何判断两个对象相等
                    return true;
                }
            }
        }

        return false;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("argument is not allow less than zero");
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("");
        }

        return (T) elements[index];
    }

    private void autoScaleCapacity() {
        if (capacity < MAX_CAPACITY && (size + 1) * 2 > capacity) {
            //当元素超过容量的一半时，需要扩容一倍
            int newCapacity = capacity * 2;
            Object[] newElements = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
            capacity = newCapacity;
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("elements", elements).add("size", size).add("capacity", capacity)
            .add("MAX_CAPACITY", MAX_CAPACITY).toString();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        System.out.println(list.size());
        System.out.println(list.indexOf(null));

        CustomArrayList<Integer> myList = new CustomArrayList<>(1);
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(1);
        myList.add(1);
        myList.add(1);
        myList.add(1);
        myList.add(1);
        myList.add(10000);
        myList.remove(2);
        myList.remove(myList.size() - 2);
        System.out.println(myList.toString());
    }

}
