package com.ptt.service;

import com.ptt.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptt.pojo.vo.PortalVo;
import com.ptt.utils.Result;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-25 16:57:21
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline,String token);

    Result updateHeadline(Headline headline);
}
