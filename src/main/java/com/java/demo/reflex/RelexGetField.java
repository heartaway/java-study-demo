package com.java.demo.reflex;

import com.java.demo.reflex.domain.RelexDemo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * <p>
 * Class 构造器  实例化  域 方法
 * 类的加载的两大步骤：装载链接，类初始化（非对象初始化）
 * 间接调用方法：反射 、Java 7 提供了另外一种方式：方法句柄。
 * http://ifeve.com/java-reflection-tutorial/
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/8/2
 * Time: 下午4:59
 */
public class RelexGetField {

    public static void main(String[] args) throws Exception {

        /**
         * Class
         **/
        //获取class的三种方式,区别与联系：
        // 1.RelexDemo.class 只是完成了类的加载和链接，并不会进行类的初始化
        // 2.Class.forName 如果对还没有加载到ClassLoad中的类进行加载，并执行初始化类(类的静态代码块会被执行)
        // 3.三者都可以获取类的Class对象，且Class对象与Class唯一加载到Perm区一样具有唯一性
        RelexDemo.class.getClassLoader().loadClass("com.java.demo.reflex.domain.RelexDemo");
        System.out.println(RelexDemo.class.getFields().length);
        System.out.println(Class.forName("com.java.demo.reflex.domain.RelexDemo").getFields().length);
        System.out.println(new RelexDemo().getClass().getFields().length);
        System.out.println(new RelexDemo().getClass() == RelexDemo.class && RelexDemo.class == Class.forName("com.java.demo.reflex.domain.RelexDemo"));

        /**
         * Constructor
         **/
        //new T 与 newInstance 的区别与联系：
        // 1.newInstance 只是调用 【无参public构造器】 初始化，不做类加载，如果类只是被加载了还没有进行初始化，实例化的时候会出发类的实例化。
        // 所以通常配合Class.forName(推荐) 或者 T.class 实现类加载；
        // 2.new T 创建类的时候，类可以没有被加载
        // 3.new T 等价于 Class.forName("T").newInstance()
        // unsafe.allocateInstance初始化，不会调用构造器
        System.out.println((Class.forName("com.java.demo.reflex.domain.RelexDemo").newInstance()));

        //getConstructors返回所有public的构造器，如果是默认构造器，也会被返回
        System.out.println(RelexDemo.class.getConstructors().length);
        //getDeclaredConstructors会返回类中声明的所有构造器忽略访问权限，如果有默认构造器，则返回默认构造器，否则返回长度为0的数组
        System.out.println(RelexDemo.class.getDeclaredConstructors().length);

        /**                                   ]
         * Method
         **/
        //getMethods 会返回类及其所有父类中的所有public方法
        //getDeclaredMethods 返回当前类声明的public方法（不包含父类）
        System.out.println(RelexDemo.class.getMethods().length);
        System.out.println(RelexDemo.class.getDeclaredMethods().length);
        Method[] methods = RelexDemo.class.getDeclaredMethods();
        //getModifiers 返回方法或者属性的访问权限修饰符 数字，配合Modifier提供的静态方法可以获取具体的修饰符字符串
        int modifiers = methods[0].getModifiers();
        System.out.println(modifiers);
        System.out.println(Modifier.toString(modifiers));
        System.out.println(RelexDemo.class.getDeclaredField("staticField").getModifiers());
        System.out.println(Modifier.toString(RelexDemo.class.getDeclaredField("staticField").getModifiers()));


        /**
         * Field
         **/
        //设置属性的可见性，只对当前取出来的属性有效，不会对原始Class产生影响
        Class clazz = RelexDemo.class;
        Field field = clazz.getDeclaredField("isMarry");
        field.setAccessible(true);
        System.out.println(field.getBoolean(new RelexDemo()));
        System.out.println(RelexDemo.class.getFields().length);


        /**
         * MethodHandle
         * https://netbeans.org/bugzilla/show_bug.cgi?id=201781
         **/
        //获取属性 findGetter、findStaticGetter
        RelexDemo relexDemo = new RelexDemo();
        relexDemo.setAge(27);
        try {
            MethodHandle ageFieldMethodHandle = MethodHandles.lookup().findGetter(RelexDemo.class, "age", int.class);
            ageFieldMethodHandle.invoke(relexDemo);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //获取方法调用 ：findVirtual 查找一般函数、findStatic 查找静态方法、findSpecial 查找私有方法
        try {
            MethodType ageSetMethodType = MethodType.methodType(void.class, int.class); //制定方法签名
            MethodHandle ageFieldMethodHandle = MethodHandles.lookup().findVirtual(RelexDemo.class, "setAge", ageSetMethodType);
            ageFieldMethodHandle.invoke(relexDemo,new Integer(1));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
