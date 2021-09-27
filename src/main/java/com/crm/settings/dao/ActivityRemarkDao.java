package com.crm.settings.dao;

import com.crm.settings.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    List<ActivityRemark> selectRemarkByActivityId(ActivityRemark remark);
    int insertRemark(ActivityRemark remark);
    int deleteRemarkById(ActivityRemark remark);
    ActivityRemark selectRemarkById(ActivityRemark remark);
    int updateRemarkById(ActivityRemark remark);
    int deleteRemarkByActivityId(ActivityRemark remark);
}
