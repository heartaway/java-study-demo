package com.java.demo.basic;

/**
 * <p>
 * hashcode的目的：
 * 1.选择质数：质数的特性（只有1和自己是因子）能够使得它和其他数相乘后得到的结果比其他方式更容易产成唯一性，也就是hash code值的冲突概率最小。
 * <p>
 * The value 31 was chosen because it is an odd prime. If it were even and the multiplication overflowed, information
 * would be lost, as multiplication by 2 is equivalent to shifting. The advantage of using a prime is less clear, but
 * it is traditional. A nice property of 31 is that the multiplication can be replaced by a shift and a subtraction
 * for better performance: 31 * i == (i << 5) - i. Modern VMs do this sort of optimization automatically.
 * </p>
 * 2. 选择31，
 *
 * @author: xinyuan.ymm
 * @create: 2016-11-11 下午2:28
 */
public class HashCodeTest {

    public static void main(String[] args) {
        int number = 15;
        System.out.println(number * 31);
        System.out.println((number << 5) - number);

        //31*N 等于 N=(N<<5)-N


    }
}
