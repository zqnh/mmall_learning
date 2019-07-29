package net.zzqd.mmall.service;

import com.github.pagehelper.PageInfo;
import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.pojo.Shipping;

/**
 * @author zqnh
 * @date 2019/7/29 on 13:53.
 */
public interface IShippingService
{
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId,Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
