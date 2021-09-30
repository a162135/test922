package com.crm.settings.web.controller;

import com.crm.settings.domain.Activity;
import com.crm.settings.domain.Clue;
import com.crm.settings.domain.ClueActivityRelation;
import com.crm.settings.domain.Tran;
import com.crm.settings.service.ClueService;
import com.crm.utils.DateTimeUtil;
import com.crm.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClueController {

    @Resource
    private ClueService service;

    @RequestMapping(value = "/clue/addClue.do")
    @ResponseBody
    public Map<String,Object> addClue(Clue clue){
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        int i = service.addClue(clue);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/clue/setBund.do")
    @ResponseBody
    public Map<String,Object> setBund(String id,String[] activityId){
        int i = service.setBund(id,activityId);
        Map<String,Object> map = new HashMap<>();
        if (i >= activityId.length){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/clue/queryBund.do")
    @ResponseBody
    public List<Activity> queryBund(String id){
        List<Activity> list = service.queryBund(id);
        return list;
    }

    @RequestMapping(value = "/clue/removeBund.do")
    @ResponseBody
    public Map<String,Object> removeBund(ClueActivityRelation relation){
        int i = service.removeBund(relation);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/clue/queryClueById.do")
    @ResponseBody
    public Clue queryClueById(String id){
        Clue clue = service.queryClueById(id);
        return clue;
    }

    @RequestMapping(value = "/clue/retweet.do")
    @ResponseBody
    public Map<String,Object> retweet(String id, Tran tran){
        Map<String,Object> map = new HashMap<>();
        if (service.retweet(id,tran)){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }
}
