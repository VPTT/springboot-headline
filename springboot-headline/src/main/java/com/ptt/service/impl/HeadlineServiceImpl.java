package com.ptt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ptt.pojo.Headline;
import com.ptt.pojo.vo.PortalVo;
import com.ptt.service.HeadlineService;
import com.ptt.mapper.HeadlineMapper;
import com.ptt.service.UserService;
import com.ptt.utils.JwtHelper;
import com.ptt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-03-25 16:57:21
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        Page<Map> page=new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        headlineMapper.selectMyPage(page,portalVo);

        Map pageInfo = new HashMap<>();
        Map Data = new HashMap<>();
        pageInfo.put("pageData",page.getRecords());
        pageInfo.put("pageNum",page.getCurrent());
        pageInfo.put("pageSize",page.getSize());
        pageInfo.put("totalPage",page.getPages());
        pageInfo.put("totalSize",page.getTotal());

        Data.put("pageInfo",pageInfo);
        return Result.ok(Data);

    }

    /*
    根据id查询详情
    查询对应数据 自定义mapper方法 map
    阅读量+1  version乐观锁
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map data=headlineMapper.queryDetailMap(hid);
        Map headlineMap=new HashMap<>();
        headlineMap.put("headline",data);

        Headline headline=new Headline();
        headline.setHid((Integer) data.get("hid"));
        headline.setVersion((Integer) data.get("version"));
        headline.setPageViews((Integer) data.get("pageViews")+1);
        headlineMapper.updateById(headline);

        return Result.ok(headlineMap);
    }

    @Override
    public Result publish(Headline headline,String token) {
        Integer uid=jwtHelper.getUserId(token).intValue();
        headline.setPublisher(uid);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    /*
    查询数据最新的version
    修改修改时间
     */
    @Override
    public Result updateHeadline(Headline headline) {
        Integer version =headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);
        return Result.ok(null);


    }


//    @Override
//    public Result findNewsPage(PortalVo portalVo) {
//
//        LambdaQueryWrapper<Headline> LambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if(portalVo.getKeyWords() !=null)
//            LambdaQueryWrapper.like(Headline::getArticle,portalVo.getKeyWords());
//        LambdaQueryWrapper.eq(Headline::getType, portalVo.getType());
//        Page<Headline> page=new Page<>();
//
//        headlineMapper.selectPage(page,LambdaQueryWrapper);
//
//
//        Map pageInfo = new HashMap<>();
//        Map Data = new HashMap<>();
//        pageInfo.put("pageData",page.getCurrent());
//        pageInfo.put("pageNum",page.getPages());
//        pageInfo.put("pageSize",page.getSize());
//        pageInfo.put("totalPage",page.getTotal());
//        pageInfo.put("totalSize",page.getTotal());
//
//        Data.put("pageInfo",pageInfo);
//        return Result.ok(Data);
//
//    }
}




