package net.zzqd.mmall.controller.backend;

import net.zzqd.mmall.common.Const;
import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.pojo.User;
import net.zzqd.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zqnh
 * @date 2019/7/25 on 17:57.
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController
{
    @Autowired
    private IUserService iUserService;

    @RequestMapping (value="login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session)
    {
        ServerResponse<User> response = iUserService.login(username,password);
        if(response.isSuccess())
        {
            User user = response.getData();
            if(user.getRole() == Const.Role.ROLE_ADMAIN)
            {
                //说明登陆的是管理员
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            }
            else
            {
                return ServerResponse.createByErrorMessage("不是管理员，无法登陆");
            }
        }
        return response;
    }
}
