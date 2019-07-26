package net.zzqd.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @author zqnh
 * @date 2019/7/25 on 11:08.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候，如果是null的对象，key也会消失
public class ServerResponse<T> implements Serializable
{
    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status)
    {
        this.status=status;
    }
    private ServerResponse(int status,T data)
    {
        this.status=status;
        this.data=data;
    }
    private ServerResponse(int status,String msg,T data)
    {
        this.status=status;
        this.msg=msg;
        this.data=data;
    }
    private ServerResponse(int status,String msg)
    {
        this.status=status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不再json的序列化结果当中
    public boolean isSuccess()
    {
        return this.status==ResponseCode.SUCCESS.getCode();
    }

    private int getStatus()
    {
        return status;
    }
    public T getData()
    {
        return data;
    }
    public String getMsg()
    {
        return msg;
    }

    public static <T> ServerResponse<T> createBySuccess()
    {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> ServerResponse<T> createBySuccessMessage(String msg)
    {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> ServerResponse<T> createBySuccess(T data)
    {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> ServerResponse<T> createBySuccess(String msg,T data)
    {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError()
    {
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage)
    {
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage)
    {
        return new ServerResponse<>(errorCode,errorMessage);
    }
}
