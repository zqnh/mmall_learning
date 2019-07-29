package net.zzqd.mmall.service;

import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.vo.CartVo;

/**
 * @author zqnh
 * @date 2019/7/28 on 20:32.
 */
public interface ICartService
{
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId,Integer productId,Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
