package com.crm.settings.service.impl;

import com.crm.settings.dao.ActivityDao;
import com.crm.settings.domain.Activity;
import com.crm.settings.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao dao;

    @Override
    @Transactional
    public int saveActivity(Activity activity) {
        int i = dao.insertActivity(activity);
        return i;
    }
}
