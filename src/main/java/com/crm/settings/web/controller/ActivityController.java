package com.crm.settings.web.controller;

import com.crm.settings.domain.Activity;
import com.crm.settings.service.ActivityService;
import com.crm.settings.vo.Page;
import com.crm.utils.DateTimeUtil;
import com.crm.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/activity/getOwner.do")
    @ResponseBody
    public List<String> getOwner(){
        return service.getOwner();
    }


    @RequestMapping(value = "/activity/queryActivity.do")
    @ResponseBody
    public List<Activity> queryActivity(Activity activity,HttpSession session){
        Page page = (Page) session.getAttribute("activityPage");
        System.out.println(page);
        if (page == null){
            page = new Page();
        }
        if (page.getPage() > page.getPageMax()){
            page.setPage(page.getPageMax());
        }

        PageHelper.startPage(page.getPage(),page.getPageSize());
        List<Activity> activities = service.queryActivity(activity);
        return activities;
    }

    @RequestMapping(value = "/activity/queryActivityPage.do")
    @ResponseBody
    public String queryActivity(Page page1, HttpSession session){
        Page page = (Page) session.getAttribute("activityPage");
        if (page == null){
            page = new Page();
        }
        if (page1.getPageSize() != null){
            page.setPageSize(page1.getPageSize());
        }
        if (page1.getPage() != null){
            page.setPage(page1.getPage());
        }
        session.setAttribute("activityPage",page);
        return "1";
    }

    @RequestMapping(value = "/activity/queryActivityPageCount.do")
    @ResponseBody
    public Map<String,Integer> queryActivityPageCount(Activity activity,HttpSession session){
        int i = service.queryActivityPage(activity);
        Page page = (Page) session.getAttribute("activityPage");
        if (page == null){
            page = new Page();
        }
        page.setPageCount(i);
        int pageMax = i / page.getPageSize();
        if (i % page.getPageSize() != 0){
            pageMax += 1;
        }
        page.setPageMax(pageMax);
        session.setAttribute("activityPage",page);
        Map<String,Integer> map = new HashMap<>();
        map.put("pageCount",i);
        map.put("pageMax",pageMax);
        return map;
    }
}
