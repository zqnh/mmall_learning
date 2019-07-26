package net.zzqd.mmall.test;

import net.zzqd.mmall.pojo.User;
import net.zzqd.mmall.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zqnh
 * @date 2019/7/26 on 12:53.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext*.xml"})
public class Test1
{
    @Autowired
    private IUserService iUserService;

    @Test
    public void test()
    {
        User user  = new User();
        user.setPassword("root");
        user.setUsername("root");
        user.setAnswer("哈哈");
        user.setQuestion("哈哈");
        user.setPhone("15151515151");
        user.setEmail("7151515@qq.com");
        user.setId(50);
        user.setRole(0);
        iUserService.register(user);
        if(iUserService.register(user)!= null)
        {
            System.out.println("注册成功");
        }
        else
        {
            System.out.println("注册失败");
        }
    }

}
