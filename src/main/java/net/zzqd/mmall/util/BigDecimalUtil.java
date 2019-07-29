package net.zzqd.mmall.util;

import java.math.BigDecimal;

/**
 * @author zqnh
 * @date 2019/7/28 on 21:43.
 */
public class BigDecimalUtil
{
    private BigDecimalUtil()
    {
        //私有构造器，不允许实例化
    }
    //加法
    public static BigDecimal add(double v1,double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }
    //减法
    public static BigDecimal sub(double v1,double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }
    //乘法
    public static BigDecimal mul(double v1,double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }
    //除法
    public static BigDecimal div(double v1,double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);//四舍五入，保留两位小数

        //除不尽的情况
    }
}
