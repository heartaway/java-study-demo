package com.java.demo.basic;

/**
 * <p>
 * 总结：
 * 1.我们在对数字进行运算的时候，转为基本类型进行运算，
 * 2.判断相等的时候，尽量保证类型一致的情况下，使用equal，或者使用基本类型判断相等,不要在equal中做逻辑运算；
 * 3.在Integer、Byte、Short、Long 中均采用了小数值得缓存池cache方案，两种浮点类型未使用。
 * 4. equals 方法需要满足自反性（x.equals(x)必须返回true）、传递性 （x.equals(y)返回true时，y.equals(x)也必须返回true）、
 * 对称性（x.equals(y)和y.equals(z)、一致性（当x和y引用的对象信息没有被修改时，多次调用x.equals(y)应该得到同样的返回值）。
 * </p>
 *
 * @author: xinyuan.ymm
 * @create: 2016-11-11 上午11:29
 */
public class IntegerEqualsTest {


    public static void main(String[] args) {
        Integer a = 100, b = 100, c = 150, d = 150;

        System.out.println(a == b);
        System.out.println(c == d);

        System.out.println(a.equals(b));
        System.out.println(c.equals(d));

        System.out.println("-------");
        Long aa = 100L, bb = 100L, cc = 150L, dd = 150L;
        System.out.println(aa == bb);
        System.out.println(cc == dd);

        //为什么呢？  当我们给一个Integer对象赋一个int值的时候，会调用Integer类的静态方法valueOf
        //对于非基本类型来说，==号两边比较的是内存地址，但是对于Integer/Long来说，会默认缓存-128到127之间的值。
        System.out.println("-------");
        Integer a1 = 1;
        Integer b1 = 2;
        Long g1 = 3L;
        System.out.println(g1 == (a1 + b1));
        System.out.println(g1.equals(a1 + b1));

        //对于 g == (a + b) ， == 右边的运算符，会引发拆箱操作，于是 a和b都拆成基本类型(int), 之后 == 就是 Long和基本类型的比较，就会用Long的值进行比较，得到true。
        //对于 g.equals(a + b) 为什么会是 fasle,源码首先判断传入的值是不是Long类型，如果不是Long类型，就直接返回false。

        //总结： 我们在对数字进行运算的时候，转为基本类型进行运算，判断相等的时候，尽量保证类型一致的情况下，使用equal，或者使用基本类型判断相等,不要在equal中做逻辑运算；
    }
}
