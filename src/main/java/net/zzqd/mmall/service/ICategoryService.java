package net.zzqd.mmall.service;

import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.pojo.Category;

import java.util.List;

/**
 * @author zqnh
 * @date 2019/7/27 on 10:08.
 */
public interface ICategoryService
{
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId,String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer cateoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
