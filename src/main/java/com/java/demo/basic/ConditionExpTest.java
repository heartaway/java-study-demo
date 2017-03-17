package com.java.demo.basic;

import org.testng.annotations.Test;

/**
 * 条件表达式
 *
 * @author: xinyuan.ymm
 * @create: 2017-03-17 上午1:15
 */
public class ConditionExpTest {

    private class Student {
        private Long age;

        public Long getAge() {
            return age;
        }
    }


    /**
     * 可以看到，下面的这段代码跑出了NPE的异常；
     * Java语言规范（jls7）中对条件表达式的类型约束：
     * 1.如果第二个和第三个参数拥有相同的类型（包括空类型null），这个类型就是条件表达式的类型；
     * 2.如果第二个和第三个参数中一个是原生类型，另一个是装箱类型，那么总体会要求是一个原生类型；（装箱的类型会被拆箱）
     * 3.如果第二和第三操作数中的一个是空类型，并且另一个的类型是引用类型，则条件表达式的类型是引用类型;
     * <p>
     * 从这里，我们可以看到，这里触发了第二点，当getAge返回null的时候，进行null.intValue(),就会报NPE
     * 所以，我们在使用条件表达式的时候，一定要尽量保证类型的一致性；
     */
    @Test
    public void testCondition() {
        Student student = new Student();
        System.out.println(student == null ? 0 : student.getAge());
    }
}
