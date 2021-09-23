package com.crm.settings.web.controller;

import com.crm.settings.domain.Activity;
import com.crm.settings.service.ActivityService;
import com.crm.utils.DateTimeUtil;
import com.crm.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ActivityController {

    @Resource
    private ActivityService service;

    @RequestMapping(value = "/activity/saveActivity.do")
    @ResponseBody
    public Object saveActivity(Activity activity){
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        int i = service.saveActivity(activity);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }
}
