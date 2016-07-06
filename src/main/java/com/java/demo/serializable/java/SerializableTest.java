package com.java.demo.serializable.java;

import com.caucho.hessian.io.HessianOutput;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Date;

/**
 * <p>
 * Java 序列化是如何实现与机器无关的？
 * <p/>
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/3/8
 * Time: 下午2:30
 */
public class SerializableTest implements Serializable {

    private int father;

    private static final long serialVersionUID = 1937803012639770720L;

    private class ObjectSaver extends SerializableTest implements Serializable {
        private static final long serialVersionUID = -1460368089309853877L;
        public String name;
        public int old;

        public ObjectSaver(String name, int old) {
            super.father = 1;
            this.name = name;
            this.old = old;
        }
    }


    @Test
    public void testWriteObject() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/Users/xinyuan/tmp/ObjectSaver-Java.obj"));
        objectOutputStream.writeObject("严明明");
        objectOutputStream.writeObject(new Date());
        objectOutputStream.writeObject(new ObjectSaver("测试类", 30));
        objectOutputStream.close();
        "".intern();
    }


    @Test
    public void testReadObjectFromDisk() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/Users/xinyuan/tmp/ObjectSaver-Java.obj"));
        System.out.println(objectInputStream.readObject());
        System.out.println(objectInputStream.readObject());
        ObjectSaver objectSaver = (ObjectSaver) objectInputStream.readObject();
        System.out.println(objectSaver.name);
        System.out.println(objectSaver.old);
    }


    @Test
    public void testSerializableByHessian() throws Exception {
        HessianOutput hessianOutput = new HessianOutput(new FileOutputStream("/Users/xinyuan/tmp/ObjectSaver-Hessian.obj"));
        hessianOutput.writeObject("严明明");
        hessianOutput.writeObject(new Date());
        hessianOutput.writeObject(new ObjectSaver("测试类", 30));
        hessianOutput.close();
    }


}
