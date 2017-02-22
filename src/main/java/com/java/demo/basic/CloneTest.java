package com.java.demo.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象的克隆，我们使用对象深拷贝的时候，经常使用ModelCopper或者BeanUtil.copyProperties,疑惑硬编码；
 * 但是带来的一些列的繁琐操作，而且性能得不到保障，我们来比对一下通过序列化实现的深度克隆方案；
 * 不过效率最高的还是 硬编码的方式进行属性拷贝；
 * <p></p>
 *
 * @author: xinyuan.ymm
 * @create: 2016-11-11 下午3:57
 */
public class CloneTest {

    static class Person implements Serializable {
        private static final long serialVersionUID = -9102017020286042305L;

        private String name;    // 姓名
        private int age;        // 年龄
        private Car car;        // 座驾

        public Person(String name, int age, Car car) {
            this.name = name;
            this.age = age;
            this.car = car;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + ", car=" + car + "]";
        }

    }


    static class Car implements Serializable {
        private static final long serialVersionUID = -5713945027627603702L;

        private String brand;       // 品牌
        private int maxSpeed;       // 最高时速

        public Car(String brand, int maxSpeed) {
            this.brand = brand;
            this.maxSpeed = maxSpeed;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getMaxSpeed() {
            return maxSpeed;
        }

        public void setMaxSpeed(int maxSpeed) {
            this.maxSpeed = maxSpeed;
        }

        @Override
        public String toString() {
            return "Car [brand=" + brand + ", maxSpeed=" + maxSpeed + "]";
        }

    }

    /**
     * 基于序列化的深拷贝
     * 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
     * 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
     *
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
    }

    public static void main(String[] args) {
        try {
            Car car = new Car("Benz", 300);
            Person p1 = new Person("Hao LUO", 33, car);
            long start = System.currentTimeMillis();
            Person p2 = clone(p1);   // 深度克隆
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            p2.getCar().setBrand("BYD");
            // 修改克隆的Person对象p2关联的汽车对象的品牌属性
            // 原来的Person对象p1关联的汽车不会受到任何影响
            // 因为在克隆Person对象时其关联的汽车对象也被克隆了
            System.out.println(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
