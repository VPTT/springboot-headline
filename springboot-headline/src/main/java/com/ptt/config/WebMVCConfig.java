package com.ptt.config;

import com.ptt.interceptor.LoginProtect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMVCConfig
 * Package: com.ptt.config
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/26 16:58
 * @Version 1.0
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
    private LoginProtect loginProtect;//已经加入容器 使用容器注入 不用自己new
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtect).addPathPatterns("/headline/**");
    }
}
