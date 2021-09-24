package com.crm.settings.dao;

import com.crm.settings.domain.User;

import java.util.List;

public interface UserDao {

    User selectUserLogin(User user);
    String selectIdByName(String loginAct);
    List<String> selectName();
}
