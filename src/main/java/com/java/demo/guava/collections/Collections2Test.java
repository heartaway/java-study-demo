package com.java.demo.guava.collections;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-16 下午8:00
 */
public class Collections2Test {

    @Test
    public void testNewLists() {
        //定义一个空大小的数组
        List<Collections2Test> collections2Tests = Lists.newArrayList();

        //限定大小的数组
        List<Collections2Test> exactly100 = Lists.newArrayListWithCapacity(100);

        //大约申请100，大约值得计算方式是：Ints.saturatedCast(5L + arraySize + (arraySize / 10))，这么计算的原理是什么呢？
        //从TODO注释上来看，之所以采用这用写法，并没有严格的研究支持，仅仅是依靠经验值。
        //5主要是为小列表考虑（长度小于10时，长度/10并不会产生任何影响），长度/10 是为大列表考虑，size很大的时候，给定大小和真正分配的容量之比约为10/11
        List<Collections2Test> approx100 = Lists.newArrayListWithExpectedSize(100);
        System.out.println(approx100.size());//==0
    }

    @Test
    public void testNewInts() {
        String myints = Ints.join(",", 1, 2, 3);
        System.out.println(myints);
        Ints.tryParse("9898");
    }

    @Test
    public void testNewStrings() {
        Strings.emptyToNull("");
        Strings.nullToEmpty(null);
        Strings.repeat("abc", 2);
    }

    @Test
    public void testNewMaps() {
        Map originMap = Maps.newHashMap();
        Maps.newHashMap(new HashMap<String, Integer>());
        Maps.newHashMapWithExpectedSize(100);
        Maps.newIdentityHashMap();
        Maps.newLinkedHashMap();
        Maps.newLinkedHashMapWithExpectedSize(100);

        originMap.put("a", "aa");
        originMap.put("b", "bb");
        originMap.put("c", "cc");
        Collection<String> filteredKeys = Arrays.asList("a", "c");
        Map filteredMap = Maps.filterKeys(originMap, filteredKeys::contains);
        System.out.println(filteredMap);
    }



    @Test
    public void testCollect2s() {
        //对于Collections工具类，我们更建议使用java8下的java.util.stream.Streams
        //Collections2.filter();
    }

}
