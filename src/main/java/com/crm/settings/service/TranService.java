package com.crm.settings.service;

import com.crm.settings.domain.*;

import java.util.List;

public interface TranService {
    List<User> getOwner();

    Customer queryAccount(String name);

    List<Contacts> queryContacts(String name);

    List<Activity> queryActivity(String name);

    int addTran(Tran tran,Customer customer);
}
