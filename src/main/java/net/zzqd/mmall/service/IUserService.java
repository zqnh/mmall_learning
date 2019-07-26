package net.zzqd.mmall.service;

import net.zzqd.mmall.common.ServerResponse;
import net.zzqd.mmall.pojo.User;

/**
 * @author zqnh
 * @date 2019/7/25 on 11:05.
 */
public interface IUserService
{
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);
}
