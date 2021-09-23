package com.crm.settings.service.impl;

import com.crm.settings.dao.UserDao;
import com.crm.settings.domain.User;
import com.crm.settings.exception.LoginException;
import com.crm.settings.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao dao;

    public User login(User user){
        User user1 =  dao.selectUserLogin(user);
        String text = "true";
        //用户名密码判断
        if (user1 == null){
            throw new LoginException("用户名或密码错误");
        }
        //账号锁定判断
        if ("0".equals(user.getLockState())){
            throw new LoginException("账号已锁定");
        }
        //用户失效判断
        if (user1.getExpireTime() != null && !user1.getExpireTime().equals("")){
            long time1 = System.currentTimeMillis();
            long time2 = 0;
            try {
                time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(user1.getExpireTime()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long time = time2 - time1;
            if (time <= 0){
                throw new LoginException("用户已失效");
            }
        } else {
            throw new LoginException("用户已失效");
        }
        return user1;
    }
}
