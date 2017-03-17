package com.java.demo.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.testng.annotations.Test;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-17 上午12:45
 */
public class StringsTest {

    @Test
    public void testStringJoinerSplit() {
        //string join
        Joiner.on(",").skipNulls().join(1, 2, 3).toString();

        //我们发现如果使用 split，只是把最后的空字符串给忽略了（结果是： [, 1, 2, , 3] )
        Print.out(",1,2,, 3,".split(","));
        //采用Splitter 的omitEmptyStrings 可以帮我们去掉空字符串，trimResults可以去掉空格
        Print.out(Splitter.on(",").trimResults().omitEmptyStrings().split(",1,2,, 3,"));

        //采用Splitter还有一个好处是，split在对一些特殊符号时需要进行转义，比如点，而Splitter不需要，一定程度上减少了出错的概率。
        Print.out("---");
        Print.out("1..2.3.".split("."));
        Print.out(Splitter.on(".").trimResults().omitEmptyStrings().split("1..2.3."));
    }



}
