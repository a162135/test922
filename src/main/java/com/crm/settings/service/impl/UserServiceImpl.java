package com.crm.settings.service.impl;

import com.crm.settings.dao.UserDao;
import com.crm.settings.domain.User;
import com.crm.settings.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao dao;

    public User login(User user){
        return dao.selectUserLogin(user);
    }
}
