package com.ptt.service;

import com.ptt.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptt.utils.Result;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-03-25 16:57:21
*/
public interface UserService extends IService<User> {
    Result login(User user);
    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);

    Result checkLogin(String token);
}
