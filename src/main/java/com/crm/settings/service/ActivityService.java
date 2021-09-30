package com.crm.settings.service;

import com.crm.settings.domain.Activity;
import com.crm.settings.vo.Page;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int saveActivity(Activity activity);
    List<Activity> getOwner();
    List<Activity> queryActivity(Activity activity);
    int queryActivityPage(Activity activity);
    int removeActivity(String[] activities);
    int setActivity(Activity activities);
    Activity queryActivityById(Activity activity);

    List<Activity> queryAllById(Activity activity);
    List<Activity> queryActivityAll(Map<String,String> map);
    List<Activity> queryActivityAll2(Map<String,String> map);
}
