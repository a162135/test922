package com.crm.settings.dao;

import com.crm.settings.domain.Activity;

import java.util.List;

public interface ActivityDao {
    int insertActivity(Activity activity);
    List<Activity> selectActivity(Activity activity);
    int selectActivityPage(Activity activity);
    int deleteActivity(String[] activities);
    int updateActivity(Activity activity);
    Activity selectActivityById(Activity activity);
    List<Activity> selectAllById(Activity activity);
    List<Activity> selectAll();

}
