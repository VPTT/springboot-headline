package com.ptt.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ptt.pojo.User;
import com.ptt.service.UserService;
import com.ptt.mapper.UserMapper;
import com.ptt.utils.JwtHelper;
import com.ptt.utils.MD5Util;
import com.ptt.utils.Result;
import com.ptt.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-03-25 16:57:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    /*
    登录业务
     1.根据账号，查询用户对象
     2.用户为null，查询失败 账号错误501
     3.对比密码 密码错误 503
     4.用户id->token  token-》result返回

     */
    public Result login(User u){
        Result<User> userResult;
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,u.getUsername());
        User user=userMapper.selectOne(queryWrapper);
        if(user==null)
        {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        if(!StringUtils.isEmpty(u.getUserPwd()) && MD5Util.encrypt(u.getUserPwd()).equals(user.getUserPwd()))
        {
            String token = jwtHelper.createToken(user.getUid().longValue());
            Map map=new HashMap<>();
            map.put("token",token);

            return Result.ok(map);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);

    }

    @Override
    public Result getUserInfo(String token) {
        if( jwtHelper.isExpiration(token))
        {
            Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        User user=userMapper.selectById( jwtHelper.getUserId(token).intValue() );
        user.setUserPwd("");
        Map data=new HashMap<>();
        data.put("loginUser",user);
        return Result.ok(data);

    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,username);
        long count=userMapper.selectCount(userLambdaQueryWrapper);
        if(count==0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        //检查用户名
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        long count=userMapper.selectCount(userLambdaQueryWrapper);
        if(count>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt( user.getUserPwd() ));
        userMapper.insert(user);
        return Result.ok(null);
    }

    @Override
    public Result checkLogin(String token) {
        if(jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }

}




