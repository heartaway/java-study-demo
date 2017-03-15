package com.java.demo.guava.collections;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import org.testng.annotations.Test;

/**
 * 不可变集合
 * <p>
 * 实践：
 * 1. 防御性变成。如果你没有修改某个集合的需求，或者希望某个集合保持不变时，把它防御性地拷贝到不可变集合是个很好的实践。
 * <p>
 * 学习连接地址：http://www.cnblogs.com/shanyou/p/4782762.html
 *
 * @author: xinyuan.ymm
 * @create: 2017-03-06 下午9:11
 */
public class ImmutableCollectionTest {

    private static final ImmutableSet<String> TYPES = ImmutableSet.of("RUN", "STOP");


    @Test
    public void testImmutableSetSort() {
        ImmutableSet<String> varImmutable = ImmutableSet.of("f", "a", "f", "d", "c", "b");
        System.out.println(varImmutable);
    }

    /**
     * 测试asList方法，asList返回的列表视图通常比一般的列表平均性能更好
     * 原因：它总是使用高效的contains方法？
     */
    @Test
    public void testImmutableSetAsList() {
        ImmutableSet<String> varImmutable = ImmutableSet.of("f", "a", "f", "d", "c", "b");
        System.out.println(varImmutable.asList());
    }

    /**
     * 可排序的不可变集合
     */
    @Test
    public void testImmutableSortSet() {
        ImmutableSortedSet<String> varImmutable = ImmutableSortedSet.of("f", "a", "f", "d", "c", "b");
        System.out.println(varImmutable.asList());
        Preconditions.checkArgument("[a, b, c, d, f]".equals(varImmutable.asList().toString()));
    }
}
