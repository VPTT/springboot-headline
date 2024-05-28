package com.ptt.controller;

import com.ptt.pojo.User;
import com.ptt.service.impl.UserServiceImpl;
import com.ptt.utils.Result;
import jakarta.servlet.http.PushBuilder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.ptt.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/26 10:56
 * @Version 1.0
 */

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("login")
    public Result login(@RequestBody User user)
    {
        Result userResult = userService.login(user);
        return userResult;

    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token)
    {
        Result userResult=userService.getUserInfo(token);
        return userResult;
    }
    @PostMapping("checkUserName")
    public Result checkUserName(String username)//(@Param("username") String username)
    {
        Result result=userService.checkUserName(username);
        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result result=userService.regist(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token)
    {
        Result result=userService.checkLogin(token);
        return result;
    }


}
