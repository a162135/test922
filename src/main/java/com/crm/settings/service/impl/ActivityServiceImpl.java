package com.crm.settings.service.impl;

import com.crm.settings.dao.ActivityDao;
import com.crm.settings.dao.UserDao;
import com.crm.settings.domain.Activity;
import com.crm.settings.service.ActivityService;
import com.crm.settings.service.UserService;
import com.crm.settings.vo.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;
    @Resource
    private UserDao userDao;

    @Override
    @Transactional
    public int saveActivity(Activity activity) {
        String id = userDao.selectIdByName(activity.getOwner());
        activity.setOwner(id);
        int i = activityDao.insertActivity(activity);
        return i;
    }

    @Override
    public List<String> getOwner() {
        return userDao.selectName();
    }

    @Override
    public List<Activity> queryActivity(Activity activity) {

        List<Activity> activities = activityDao.selectActivity(activity);
        return activities;
    }

    @Override
    public int queryActivityPage(Activity activity) {

        return activityDao.selectActivityPage(activity);
    }
}
