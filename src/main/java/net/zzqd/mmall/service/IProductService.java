package net.zzqd.mmall.service;

import com.github.pagehelper.PageInfo;
import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.pojo.Product;
import net.zzqd.mmall.vo.ProductDetailVo;

/**
 * @author zqnh
 * @date 2019/7/27 on 20:52.
 */
public interface IProductService
{
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId,Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo>  searchProduct(String productName,Integer productId,int pageNum,int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);

}
