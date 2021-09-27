package com.crm.settings.service.impl;

import com.crm.settings.dao.ActivityRemarkDao;
import com.crm.settings.domain.ActivityRemark;
import com.crm.settings.service.ActivityRemarkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Resource
    private ActivityRemarkDao remarkDao;

    @Override
    public List<ActivityRemark> queryRemark(ActivityRemark remark) {
        List<ActivityRemark> list = remarkDao.selectRemarkByActivityId(remark);
        return list;
    }

    @Transactional
    @Override
    public int removeRemark(ActivityRemark remark) {
        int i = remarkDao.deleteRemarkById(remark);
        return i;
    }

    @Transactional
    @Override
    public int addRemark(ActivityRemark remark) {
        int i = remarkDao.insertRemark(remark);
        return i;
    }

    @Transactional
    @Override
    public int setRemark(ActivityRemark remark) {
        int i = remarkDao.updateRemarkById(remark);
        return i;
    }
}
