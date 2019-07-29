package net.zzqd.mmall.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zqnh
 * @date 2019/7/28 on 21:17.
 */

public class BigDecimalTest
{
    //  //商业中一定要用BigDecimal的String构造器
    @Test
    public void test()
    {
        System.out.println(0.05+0.01);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(123.3/100);
    }
    @Test
    public void test2()
    {
        BigDecimal b1 = new BigDecimal(0.05);
        BigDecimal b2 = new BigDecimal(0.01);
        System.out.println(b1.add(b2));
    }
    @Test
    public void test3()
    {
        BigDecimal b1 = new BigDecimal("0.05");
        BigDecimal b2 = new BigDecimal("0.01");
        System.out.println(b1.add(b2));
    }
}
