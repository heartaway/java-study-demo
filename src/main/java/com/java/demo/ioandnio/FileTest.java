package com.java.demo.ioandnio;


import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/4/10
 * Time: 下午5:56
 */
public class FileTest {


    /**
     * 尝试学习Channel的使用
     * ByteBuffer 是 channel读写数据的唯一入口
     *
     * @throws Exception
     */
    @Test
    public void testChannelUse() throws Exception {
        FileChannel fc = new FileOutputStream("/Users/xinyuan/tmp/file.txt").getChannel();
        FileLock fileLock = fc.tryLock();
        if (fileLock != null) {
            fc.write(ByteBuffer.wrap("1.严明明要学习Java nio技术".getBytes()));
            fc.write(ByteBuffer.wrap("2.追加一些文字".getBytes()));
        }
        fc.close();
    }


    /**
     * 由于 FileLock.tryLock() 文件锁是线程持有，所以 当未释放文件锁再去tryLock时出现
     * java.nio.channels.OverlappingFileLockException
     * fileLock.isShared() 是否是共享锁 ，共享锁:共享读操作，但只能一个写 ，排它锁:只有一个读或一个写
     * shared的含义:是否使用共享锁,一些不支持共享锁的操作系统,将自动将共享锁改成排它锁.
     * 测试用例中发现mac下fileLock为排它锁，所以当线程1在写操作的时候，线程3是读取不到数据的，虽然没有抛异常
     *
     * @throws Exception
     */
    @Test
    public void testFileLock() throws Exception {
        final FileChannel fc = new FileOutputStream("/Users/xinyuan/tmp/file.txt").getChannel();
        final FileChannel fc4Read = new FileInputStream("/Users/xinyuan/tmp/file.txt").getChannel();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileLock fileLock = fc.tryLock();
                    if (fileLock != null) {
                        System.out.println("Thread 1 获取到文件锁");
                    }

                    TimeUnit.SECONDS.sleep(10);
                    fileLock.release();
                    fileLock.close();
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    try {
                        fc.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileLock fileLock = fc.tryLock();
                    if (fileLock != null) {
                        System.out.println("Thread 2 获取到文件锁");
                    } else {
                        System.out.println("Thread 2 无法获取到文件锁");
                    }

                } catch (Exception e) {
                    System.out.println("线程2获取锁异常" + e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                    fc4Read.read(byteBuffer);
                    Charset charset = Charset.forName("UTF-8");
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer charBuffer = decoder.decode(byteBuffer);
                    byteBuffer.flip();

                    System.out.println("线程3 成功读取数据:" + charBuffer.toString());
                    fc4Read.close();
                } catch (Exception e) {
                    System.out.println("线程3获取锁异常" + e);
                }
            }
        }).start();

    }


    /**
     * 文件随机读写，处理一些大文件非常有用  ,但是在大文件中间插入数据是非常耗时的，因为要移动数据
     *
     * @throws Exception
     */
    @Test
    public void testRandomAccessFile() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("/Users/xinyuan/tmp/file.txt"), "rw");
        randomAccessFile.seek(randomAccessFile.length());
        long pointIndex = randomAccessFile.getFilePointer();
        System.out.println(pointIndex);
        randomAccessFile.write(";我是追加的内容.".getBytes());
    }


}
