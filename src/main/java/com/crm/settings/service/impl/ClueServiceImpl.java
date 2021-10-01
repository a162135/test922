package com.crm.settings.service.impl;

import com.crm.settings.dao.ClueDao;
import com.crm.settings.dao.ContactsDao;
import com.crm.settings.dao.CustomerDao;
import com.crm.settings.dao.TranDao;
import com.crm.settings.domain.*;
import com.crm.settings.service.ClueService;
import com.crm.utils.DateTimeUtil;
import com.crm.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    @Resource
    private ClueDao dao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private ContactsDao contactsDao;
    @Resource
    private TranDao tranDao;

    @Override
    public int addClue(Clue clue) {
        int i = dao.insert(clue);
        return i;
    }

    @Override
    @Transactional
    public int setBund(String id, String[] activityId) {
        ClueActivityRelation relation = new ClueActivityRelation();
        int j = 0;
        for (int i = 0; i < activityId.length; i++) {
            relation.setId(UUIDUtil.getUUID());
            relation.setClueId(id);
            relation.setActivityId(activityId[i]);
            j += dao.insertBund(relation);
        }
        return j;
    }

    @Override
    public List<Activity> queryBund(String id) {
        ClueActivityRelation relation = new ClueActivityRelation();
        relation.setClueId(id);
        List<Activity> list = dao.selectActivity(relation);
        return list;
    }

    @Override
    public int removeBund(ClueActivityRelation relation) {
        return dao.deleteBund(relation);
    }

    @Override
    public Clue queryClueById(String id) {
         return dao.selectById(id);
    }

    @Transactional
    @Override
    public boolean retweet(String id,Tran tran) {
        boolean yes = true;
        Clue clue = dao.selectAllById(id);
        String customerId = UUIDUtil.getUUID();
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setCreateTime(DateTimeUtil.getSysTime());
        contacts.setCreateBy(tran.getCreateBy());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setOwner(clue.getOwner());
        contacts.setJob(clue.getJob());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setSource(clue.getSource());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setAddress(clue.getAddress());
        contacts.setCustomerId(customerId);
        if (contactsDao.insert(contacts) <= 0){
            yes = false;
        }


        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setCreateTime(DateTimeUtil.getSysTime());
        customer.setCreateBy(tran.getCreateBy());
        customer.setOwner(clue.getOwner());
        customer.setName(clue.getCompany());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setAddress(clue.getAddress());
        if (customerDao.insert(customer) <= 0){
            yes = false;
        }

        if (tran != null && tran.getName() != null){
            tran.setOwner(clue.getOwner());
            tran.setContactsId(contacts.getId());
            tran.setCustomerId(customer.getId());
            tran.setId(customerId);
            tran.setCreateTime(DateTimeUtil.getSysTime());
            if (tranDao.insert(tran) <= 0){
                yes = false;
            }
            TranHistory history = new TranHistory();
            history.setId(UUIDUtil.getUUID());
            history.setCreateTime(DateTimeUtil.getSysTime());
            history.setCreateBy(tran.getCreateBy());
            history.setStage(tran.getStage());
            history.setMoney(tran.getMoney());
            history.setExpectedDate(tran.getExpectedDate());
            history.setTranId(tran.getId());
            if (tranDao.insertHistory(history) <= 0){
                yes = false;
            }
        }

        List<ClueActivityRelation> list = dao.selectBund(clue.getId());
        for (int i = 0; i < list.size(); i++) {
            ContactsActivityRelation relation = new ContactsActivityRelation();
            relation.setId(UUIDUtil.getUUID());
            relation.setContactsId(contacts.getId());
            relation.setActivityId(list.get(i).getActivityId());
            if (contactsDao.insertBund(relation) <= 0){
                yes = false;
            }
        }

        if (dao.deleteBund2(clue.getId()) <= 0){
            yes = false;
        }

        if (dao.deleteById(id) <= 0){
            yes = false;
        }


        return yes;
    }


}
