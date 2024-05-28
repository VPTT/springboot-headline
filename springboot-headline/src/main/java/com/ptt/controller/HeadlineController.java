package com.ptt.controller;

import com.ptt.pojo.Headline;
import com.ptt.service.HeadlineService;
import com.ptt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: HeadlineController
 * Package: com.ptt.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/26 16:39
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("headline")
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline,@RequestHeader String token)
    {
        Result result=headlineService.publish(headline,token);
        return result;

    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid)
    {

        Headline headline = headlineService.getById(hid);
        Map data =new HashMap<>();
        data.put("headline",headline);
        return Result.ok(data);


    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline)
    {
        Result result=headlineService.updateHeadline(headline);
        return result;

    }
    @PostMapping("removeByHid")
    public Result delete(Integer hid)
    {
        headlineService.removeById(hid);//逻辑删除
        return Result.ok(null);
    }


}
