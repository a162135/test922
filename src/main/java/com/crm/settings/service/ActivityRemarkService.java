package com.crm.settings.service;

import com.crm.settings.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    List<ActivityRemark> queryRemark(ActivityRemark remark);
    int removeRemark(ActivityRemark remark);
    int addRemark(ActivityRemark remark);
    int setRemark(ActivityRemark remark);
}
