package com.java.demo.constantspool;


import org.testng.annotations.Test;

/**
 * <p>
 * String 常量池
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/3/27
 * Time: 下午9:19
 */
public class StringPool {

    @Test
    public void testStringCreateAndIntern() {
        String ss = "abc";
        char data[] = {'a', 'b', 'c'};
        String sss = new String(data);
        System.out.println(ss == sss);//FALSE
        String bb = "ab";
        bb = bb.concat("c");
        System.out.println(ss == bb);//FALSE
        String cc = "ab" + "c";
        System.out.println(ss == cc);//TRUE


        String a = new String("1");//创两了2个对象，一个是堆中的Object，一个是常量池（Jdk7后也在堆中）中“1”字符串
        a.intern();
        String b = "1";

        System.out.println(a == b);
        //FALSE ，原因是new String("1")已经在常量池中闯将了一个"1"的字符创，intern方法后，发现常量池已经存在，故直接返回，a的引用还是堆中的object；
        //而 b 的引用指向的是常量池中的字符串。

        String c = new String("1") + new String("1");
        c.intern();
        String d = "11";
        System.out.println(c == d);//TRUE
        //结果是  false   true， 为什么呢？c的创建在堆中创建了两个值为1的中间匿名Object，一个常量池为1的字符串（如果不存在），和一个c指向的对象。
        //intern 时，常量池中还没有新建11字符串，则会新建对象，由于Jdk7以后，方法区放在堆中，堆中已经存在一个值为11的String，所以直接使用了此对象的引用地址，此时 c 和方法区中都指向堆中的这个Object；


        String e = new String("1");
        String f = "1";
        e.intern();
        System.out.println(e == f); //FALSE

        String g = new String("1") + new String("1");
        String h = "11";
        g.intern();
        System.out.println(g == h); //FALSE
        //结果是  false   false， 为什么呢？明白了上面的原理，这里应该就非常清楚了。
    }
}
