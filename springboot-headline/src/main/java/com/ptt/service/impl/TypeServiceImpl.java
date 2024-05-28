package com.ptt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ptt.pojo.Type;
import com.ptt.service.TypeService;
import com.ptt.mapper.TypeMapper;
import com.ptt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-03-25 16:57:21
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Result findAllTypes() {
        List<Type> types= typeMapper.selectList(null);
        return Result.ok(types);

    }
}




