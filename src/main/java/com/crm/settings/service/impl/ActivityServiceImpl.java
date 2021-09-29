package com.crm.settings.service.impl;

import com.crm.settings.dao.ActivityDao;
import com.crm.settings.dao.ActivityRemarkDao;
import com.crm.settings.dao.UserDao;
import com.crm.settings.domain.Activity;
import com.crm.settings.domain.ActivityRemark;
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
    @Resource
    private ActivityRemarkDao remarkDao;

    @Override
    @Transactional
    public int saveActivity(Activity activity) {
        int i = activityDao.insertActivity(activity);
        return i;
    }

    @Override
    public List<Activity> getOwner() {
        return userDao.selectIdAndName();
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

    @Override
    @Transactional
    public int removeActivity(String[] activities) {
        for (int i = 0; i < activities.length; i++) {
            remarkDao.deleteRemarkByActivityId(activities[i]);
        }
        int i = activityDao.deleteActivity(activities);
        return i;
    }

    @Override
    @Transactional
    public int setActivity(Activity activities) {
        int i = activityDao.updateActivity(activities);
        return i;
    }

    @Override
    public Activity queryActivityById(Activity activity) {
        return activityDao.selectActivityById(activity);
    }

    @Override
    public List<Activity> queryAllById(Activity activity) {
        return activityDao.selectAllById(activity);
    }

    @Override
    public List<Activity> queryActivityAll() {
        return activityDao.selectAll();
    }
}
