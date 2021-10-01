package com.crm.settings.dao;

import com.crm.settings.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int insertActivity(Activity activity);
    List<Activity> selectActivity(Activity activity);
    int selectActivityPage(Activity activity);
    int deleteActivity(String[] activities);
    int updateActivity(Activity activity);
    Activity selectActivityById(Activity activity);
    List<Activity> selectAllById(Activity activity);
    List<Activity> selectAll(Map<String,String> map);
    List<Activity> selectAll2(Map<String,String> map);

    List<Activity> selectListByName(@Param(value = "name") String name);
}
