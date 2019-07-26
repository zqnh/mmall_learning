package net.zzqd.mmall.common;

/**
 * @author zqnh
 * @date 2019/7/25 on 13:13.
 */
public class Const
{
    public static final String CURRENT_USER="currentUser";

    public static final String EMAIL="email";
    public static final String USERNAME="username";

    public interface Role
    {
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMAIN = 1;//管理员
    }
}
