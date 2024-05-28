package com.ptt.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptt.utils.JwtHelper;
import com.ptt.utils.Result;
import com.ptt.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: LoginProtect
 * Package: com.ptt.interceptor
 * Description:
 *  登录保护拦截器  检查请求头是否包含有效token
 * @Author ptt
 * @Create 2024/3/26 16:50
 * @Version 1.0
 */
@Component
public class LoginProtect implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        //检查是否有效
        String token=request.getHeader("token");
        if( !jwtHelper.isExpiration(token)) //token有效则不拦截
        {
            return true;
        }
        //将错误代码返回  result->json写回
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        //ObjectMapper  java对象《-》json
        ObjectMapper objectMapper=new ObjectMapper();
        response.getWriter().print(objectMapper.writeValueAsString(result));
        return false;

    }
}
