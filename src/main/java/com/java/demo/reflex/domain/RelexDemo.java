package com.java.demo.reflex.domain;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/8/2
 * Time: 下午4:59
 */
public class RelexDemo {

    public static int staticField  = 0;

    public int age;

    protected String name;

    private boolean isMarry;

    static {
        System.out.println("我是静态代码块，我被初始化了");
    }

    public RelexDemo() {
    }

    public RelexDemo(int age) {
        this.age = age;
    }

    protected RelexDemo(String name) {
        this.name = name;
    }

    private RelexDemo(boolean isMarry) {
        this.isMarry = isMarry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsMarry() {
        return isMarry;
    }

    public void setIsMarry(Boolean isMarry) {
        this.isMarry = isMarry;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarry() {
        return isMarry;
    }

    public void setMarry(boolean isMarry) {
        this.isMarry = isMarry;
    }
}
