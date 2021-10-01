package com.crm.settings.service.impl;

import com.crm.settings.dao.*;
import com.crm.settings.domain.*;
import com.crm.settings.service.TranService;
import com.crm.utils.DateTimeUtil;
import com.crm.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TranServiceImpl implements TranService {

    @Resource
    private UserDao userDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private ContactsDao contactsDao;
    @Resource
    private ActivityDao activityDao;
    @Resource
    private TranDao tranDao;


    @Override
    public List<User> getOwner() {
        return userDao.selectUser();
    }

    @Override
    public Customer queryAccount(String name) {
        return customerDao.selectByName(name);
    }

    @Override
    public List<Contacts> queryContacts(String name) {
        return contactsDao.selectListByName(name);
    }

    @Override
    public List<Activity> queryActivity(String name) {
        return activityDao.selectListByName(name);
    }

    @Transactional
    @Override
    public int addTran(Tran tran,Customer customer) {
        int yes = 1;
        if (customer != null){
            //创建客户
            customer.setId(UUIDUtil.getUUID());
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setCreateBy(tran.getCreateBy());
            if (customerDao.insert(customer) <= 0){
                yes = 0;
            }
        }
        tran.setId(UUIDUtil.getUUID());
        tran.setCreateTime(DateTimeUtil.getSysTime());
        if (tranDao.insert(tran) <= 0){
            yes = 0;
        }
        return yes;
    }
}
