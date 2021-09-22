package com.crm.settings.web.controller;

import com.crm.settings.domain.User;
import com.crm.settings.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class UserController {

    @Resource
    private UserService service;

    @RequestMapping(value = "/user/login.do", produces= "text/plain;charset=utf-8")
    @ResponseBody
    public String login(String username,String password) throws ParseException {
        User user = new User();
        user.setLoginAct(username);
        user.setLoginPwd(password);
        User user1 = service.login(user);
        String text = "true";
        //用户名密码判断
        if (user1 == null){
            text = "用户名或密码错误。";
            return text;
        }
        //账号锁定判断
        if ("0".equals(user.getLockState())){
            text = "账号已锁定";
        }
        //用户失效判断
        if (user1.getExpireTime() != null && !user1.getExpireTime().equals("")){
            long time1 = System.currentTimeMillis();
            long time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(user1.getExpireTime()).getTime();
            long time = time2 - time1;
            if (time <= 0){
                text = "用户已失效";
                return text;
            }
        } else {
            text = "用户已失效";
            return text;
        }
        //ip判断

        return text;
    }

}
