package net.zzqd.mmall.service;

import net.zzqd.mmall.common.ServerResponse;

import java.util.Map;

/**
 * @author zqnh
 * @date 2019/7/30 on 10:11.
 */
public interface IOrderService
{
    ServerResponse pay(Long orderNo, Integer userId, String path);
    ServerResponse aliCallback(Map<String,String> params);
    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
}
