package com.java.demo.basic;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * 二维数组中，按列读取，比按照行读取要快很多，原因是，行数据是连续的；
 * 专利论文：https://google.com/patents/CN1828773A?cl=zh
 * 为了应对列数据因为不连续导致读取性能地下的问题，一般的解决方案有：矩阵转换；
 *
 * @author: xinyuan.ymm
 * @create: 2017-03-01 下午8:41
 */
public class LineColumnReadTest {

    private static final int max = 2000;
    public static Integer[][] arrayList = new Integer[max][max];

    public static final int loop = 10;


    static {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                arrayList[i][j] = i + j;
            }
        }
    }

    /**
     * 执行一次行读
     *
     * @return
     */
    private long singleLineRead(int row, int col) {
        long start = System.currentTimeMillis();
        Integer count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count += arrayList[i][j];
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private long singleColumnRead(int row, int col) {
        long start = System.currentTimeMillis();
        Integer count = 0;
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                count += arrayList[i][j];
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * 这里行列读取写的有问题，高效的二位数组随机读取，还需要进一步研究
     *
     * @param row
     * @param col
     * @return
     */
    private long singleRowColumnRead(int row, int col) {
        long start = System.currentTimeMillis();
        Integer count = 0;
        for (int j = 0; j < col; j++) {
            count += arrayList[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count += arrayList[i][j];
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * 行读取
     */
    @Test
    public void moreLineRead() {
        long result = 0;
        Random random = new Random();
        int row = 1900 + random.nextInt(1);
        int col = 1900 + random.nextInt(1);
        for (int k = 0; k < loop; k++) {
            result += singleLineRead(row, col);
        }
        System.out.println("million: " + result);
    }

    /**
     * 列读取
     */
    @Test
    public void moreColumnRead() {
        long result = 0;
        Random random = new Random();
        int row = 1900 + random.nextInt(1);
        int col = 1900 + random.nextInt(1);
        for (int k = 0; k < loop; k++) {
            result += singleColumnRead(row, col);
        }
        System.out.println("million: " + result);
    }

    /**
     * 行列读取
     */
    @Test
    public void moreRowColumnRead() {
        long result = 0;
        Random random = new Random();
        int row = 1900 + random.nextInt(1);
        int col = 1900 + random.nextInt(1);
        for (int k = 0; k < loop; k++) {
            result += singleRowColumnRead(row, col);
        }
        System.out.println("million: " + result);
    }



}
