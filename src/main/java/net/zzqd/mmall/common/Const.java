package net.zzqd.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author zqnh
 * @date 2019/7/25 on 13:13.
 */
public class Const
{
    public static final String CURRENT_USER="currentUser";

    public static final String EMAIL="email";
    public static final String USERNAME="username";

    public interface ProductListOrderBy
    {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
    public interface Cart
    {
        int CHECKED = 1;//购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public interface Role
    {
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMAIN = 1;//管理员
    }

    public enum ProductStatusEnum
    {
        ON_SALE(1,"在线");
        private String value;
        private int code;
        ProductStatusEnum(int code,String value)
        {
            this.code = code;
            this.value=value;
        }

        public String getValue()
        {
            return value;
        }

        public int getCode()
        {
            return code;
        }
    }
}
