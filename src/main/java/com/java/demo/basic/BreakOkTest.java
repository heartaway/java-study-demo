package com.java.demo.basic;

/**
 * <p></p>
 *
 * @author: xinyuan.ymm
 * @create: 2016-11-11 下午3:42
 */
public class BreakOkTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                if (i == 5 && k == 3) {
                    break;
                }
            }
            System.out.print(i + ",");
        }

        System.out.println("-----");

        OK:
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                if (i == 5 && k == 3) {
                    break OK;
                }
            }
            System.out.print(i + ",");
        }
    }
}
