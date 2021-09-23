package com.crm.settings.web.controller;

import com.crm.settings.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SessionAttributeUtil {

    @RequestMapping(value = "/util/getAttribute.do")
    @ResponseBody
    public Object getAttribute(String name,HttpSession session){
        User user = (User) session.getAttribute(name);
        return user;
    }
}
