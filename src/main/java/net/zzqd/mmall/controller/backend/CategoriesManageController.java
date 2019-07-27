package net.zzqd.mmall.controller.backend;

import net.zzqd.mmall.common.Const;
import net.zzqd.mmall.common.ResponseCode;
import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.pojo.User;
import net.zzqd.mmall.service.ICategoryService;
import net.zzqd.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @author zqnh
 * @date 2019/7/27 on 9:58.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoriesManageController
{
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value="parentId",defaultValue = "0")int parentId)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null)
        {
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode()+"用户未登录，请登录");
        }
        //检验一下是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess())
        {
            //是管理员
            //增加我们处理分类的逻辑
            return iCategoryService.addCategory(categoryName,parentId);
        }
        else
        {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null)
        {
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode()+"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess())
        {
            //更新categoryName
            return iCategoryService.updateCategoryName(categoryId,categoryName);
        }
        else
        {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null)
        {
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode()+"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess())
        {
           //查询子节点的category信息，并且不递归，保持平稳
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }
        else
        {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null)
        {
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode()+"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess())
        {
            //查询当前结点的id和递归子节点的id
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }
        else
        {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }
}
