package com.crm.settings.service;

import com.crm.settings.domain.*;

import java.util.List;
import java.util.Map;

public interface TranService {
    List<User> getOwner();

    Customer queryAccount(String name);

    List<Contacts> queryContacts(String name);

    List<Activity> queryActivity(String name);

    int addTran(Tran tran,Customer customer);

    Tran queryAllById(Tran tran);

    List<TranHistory> queryHistory(TranHistory history);

    List<DicValue> queryStage();

    int setStage(Tran tran,TranHistory history);

    List<Map<String, Object>> queryStageCount();
}
