package com.ptt.service;

import com.ptt.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptt.utils.Result;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-03-25 16:57:21
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
