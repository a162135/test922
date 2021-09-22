package com.crm.settings.dao;

import com.crm.settings.domain.User;

public interface UserDao {

    User selectUserLogin(User user);
}
