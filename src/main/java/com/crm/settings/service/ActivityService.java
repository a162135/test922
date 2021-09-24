package com.crm.settings.service;

import com.crm.settings.domain.Activity;
import com.crm.settings.vo.Page;

import java.util.List;

public interface ActivityService {
    int saveActivity(Activity activity);
    List<String> getOwner();
    List<Activity> queryActivity(Activity activity);
    int queryActivityPage(Activity activity);
}
