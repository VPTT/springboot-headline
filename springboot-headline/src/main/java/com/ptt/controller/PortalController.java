package com.ptt.controller;

import com.ptt.pojo.Headline;
import com.ptt.pojo.vo.PortalVo;
import com.ptt.service.HeadlineService;
import com.ptt.service.TypeService;
import com.ptt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProtalController
 * Package: com.ptt.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/26 14:54
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("portal")
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes()
    {
        Result result=typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo)
    {
        Result result=headlineService.findNewsPage(portalVo);
        return result;

    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid)
    {
        Result result=headlineService.showHeadlineDetail(hid);
        return result;
    }








}
