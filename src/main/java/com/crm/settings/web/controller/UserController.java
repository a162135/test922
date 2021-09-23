package com.crm.settings.web.controller;

import com.crm.settings.domain.User;
import com.crm.settings.service.UserService;
import com.crm.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService service;

    @RequestMapping(value = "/user/login.do")
    @ResponseBody
    public Object login(String username, String password, HttpSession session) throws ParseException {
        User user = new User();
        user.setLoginAct(username);
        user.setLoginPwd(MD5Util.getMD5(password));
        User user1 = service.login(user);

        //登录成功
        session.setAttribute("user",user1);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

}
