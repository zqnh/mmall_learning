package net.zzqd.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import net.zzqd.mmall.common.Const;
import net.zzqd.mmall.common.ResponseCode;
import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.dao.CategoryMapper;
import net.zzqd.mmall.dao.ProductMapper;
import net.zzqd.mmall.pojo.Category;
import net.zzqd.mmall.pojo.Product;
import net.zzqd.mmall.service.ICategoryService;
import net.zzqd.mmall.service.IFileService;
import net.zzqd.mmall.service.IProductService;
import net.zzqd.mmall.util.DateTimeUtil;
import net.zzqd.mmall.util.PropertiesUtil;
import net.zzqd.mmall.vo.ProductDetailVo;
import net.zzqd.mmall.vo.ProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zqnh
 * @date 2019/7/27 on 20:53.
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService
{

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ICategoryService iCategoryService;

    public ServerResponse saveOrUpdateProduct(Product product)
    {
        if(product !=null)
        {
            if(StringUtils.isNotBlank(product.getSubImages()))
            {
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length>0)
                {
                    product.setMainImage(subImageArray[0]);
                }
            }
            if(product.getId() != null)
            {
                int rowCount = productMapper.updateByPrimaryKey(product);
                if(rowCount > 0)
                {
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                return ServerResponse.createBySuccess("更新产品失败");
            }else
            {
                int rowCount =  productMapper.insert(product);
                if(rowCount>0)
                {
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                return ServerResponse.createBySuccess("新增产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }


    public ServerResponse<String> setSaleStatus(Integer productId,Integer status)
    {
        if(productId == null||status == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount>0)
        {
            return ServerResponse.createBySuccess("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态成功");
    }

    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId)
    {
        if(productId == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        if(product == null)
        {
            return ServerResponse.createByErrorMessage("产品已经下架或者删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetaiVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    private ProductDetailVo assembleProductDetaiVo(Product product)
    {
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImage(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(productDetailVo.getDetail());
        productDetailVo.setName(productDetailVo.getName());
        productDetailVo.setStatus(productDetailVo.getStatus());
        productDetailVo.setStock(product.getStock());

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));

        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null)
        {
            productDetailVo.setParentCategoryId(0);//默认根结点
        }
        else
        {
            productDetailVo.setParentCategoryId(category.getParentId());
        }

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }


    public ServerResponse<PageInfo> getProductList(int pageNum,int pageSize)
    {
        //startPage
        //填充自己的sql
        //pageHelper收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectList();
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList)
        {
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);


    }

    private ProductListVo assembleProductListVo(Product product)
    {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));
        productListVo.setMainImage(product.getMainImage());
        productListVo.setPrice(product.getPrice());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setStatus(product.getStatus());
        return productListVo;
    }


    public ServerResponse<PageInfo>  searchProduct(String productName,Integer productId,int pageNum,int pageSize)
    {
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(productName))
        {
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectByNameAndProductId(productName,productId);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList)
        {
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId)
    {
        if(productId == null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null)
        {
            return ServerResponse.createByErrorMessage("产品已经下架或者删除");
        }
        if(product.getStatus() != Const.ProductStatusEnum.ON_SALE.getCode())
        {
            return ServerResponse.createByErrorMessage("产品已经下架或者删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetaiVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy){
        if(StringUtils.isBlank(keyword) && categoryId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();

        if(categoryId != null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null && StringUtils.isBlank(keyword)){
                //没有该分类,并且还没有关键字,这个时候返回一个空的结果集,不报错
                PageHelper.startPage(pageNum,pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Product> productList = productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);

        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product product : productList){
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }



}
