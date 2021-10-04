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
import java.util.Map;

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
    @Resource
    private DicDao dicDao;


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
        TranHistory history = new TranHistory();
        history.setId(UUIDUtil.getUUID());
        history.setStage(tran.getStage());
        history.setCreateBy(tran.getCreateBy());
        history.setCreateTime(DateTimeUtil.getSysTime());
        history.setMoney(tran.getMoney());
        history.setExpectedDate(tran.getExpectedDate());
        history.setTranId(tran.getId());
        if (tranDao.insertHistory(history) <= 0){
            yes = 0;
        }
        return yes;
    }

    @Override
    public Tran queryAllById(Tran tran) {
        return tranDao.selectAllById(tran);
    }

    @Override
    public List<TranHistory> queryHistory(TranHistory history) {
        return tranDao.selectHistoryByTranId(history);
    }

    @Override
    public List<DicValue> queryStage(){
        return dicDao.selectStage();
    }

    @Transactional
    @Override
    public int setStage(Tran tran,TranHistory history) {
        int i = 1;
        if (tranDao.updateStageById(tran) <= 0){
            i = 0;
        }
        history.setId(UUIDUtil.getUUID());
        history.setCreateTime(DateTimeUtil.getSysTime());
        history.setCreateBy(tran.getEditBy());
        history.setTranId(tran.getId());
        String stageId = dicDao.selectIdByValue(history.getStage());
        history.setStage(stageId);
        if (tranDao.insertHistory(history) <= 0){
            i = 0;
        }
        return i;
    }

    @Override
    public List<Map<String, Object>> queryStageCount() {
        return tranDao.selectStageGroup();
    }
}
