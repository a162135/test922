package com.crm.settings.web.controller;

import com.crm.settings.domain.ActivityRemark;
import com.crm.settings.service.ActivityRemarkService;
import com.crm.utils.DateTimeUtil;
import com.crm.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityRemarkController {

    @Resource
    private ActivityRemarkService service;

    @RequestMapping(value = "/activityRemark/queryRemark.do")
    @ResponseBody
    public List<ActivityRemark> queryRemark(ActivityRemark remark){
        List<ActivityRemark> list = service.queryRemark(remark);
        return list;
    }

    @RequestMapping(value = "/activityRemark/removeRemark.do")
    @ResponseBody
    public Map<String,Object> removeRemark(ActivityRemark remark){
        int i = service.removeRemark(remark);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/activityRemark/addRemark.do")
    @ResponseBody
    public Map<String,Object> addRemark(ActivityRemark remark){
        remark.setId(UUIDUtil.getUUID());
        remark.setCreateTime(DateTimeUtil.getSysTime());
        int i = service.addRemark(remark);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/activityRemark/setRemark.do")
    @ResponseBody
    public Map<String,Object> setRemark(ActivityRemark remark){
        remark.setEditFlag("1");
        remark.setEditTime(DateTimeUtil.getSysTime());
        int i = service.setRemark(remark);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }
}
