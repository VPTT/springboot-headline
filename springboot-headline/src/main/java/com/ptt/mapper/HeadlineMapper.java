package com.ptt.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ptt.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ptt.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-03-25 16:57:21
* @Entity com.ptt.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




