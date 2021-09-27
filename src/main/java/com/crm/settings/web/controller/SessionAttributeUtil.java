package com.crm.settings.web.controller;

import com.crm.settings.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SessionAttributeUtil {

    @RequestMapping(value = "/util/getAttribute.do")
    @ResponseBody
    public Object getAttribute(String name,HttpSession session){
        User user = (User) session.getAttribute(name);
        return user;
    }

    @RequestMapping(value = "/util/getAttributeString.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String getAttributeString(String name,HttpSession session){
        return (String) session.getAttribute(name);
    }

    @RequestMapping(value = "/util/setAttributeString.do")
    @ResponseBody
    public Map<String,Object> setAttributeString(String name,String data,HttpSession session){
        session.setAttribute(name,data);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
