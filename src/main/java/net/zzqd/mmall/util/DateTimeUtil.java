package net.zzqd.mmall.util;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.util.Date;

/**
 * @author zqnh
 * @date 2019/7/27 on 21:51.
 */
public class DateTimeUtil
{
    //str->Date
    //Date->str
    public static final String STANDARD_FDRMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date strToDate(String dateTimeStr, String formatStr)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date,String formatStr)
    {
        if(date == null)
        {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static void main(String[] args)
    {
        System.out.println(DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
    }

    public static Date strToDate(String dateTimeStr)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FDRMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date)
    {
        if(date == null)
        {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FDRMAT);
    }


}
