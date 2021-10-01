package com.crm.settings.dao;

import com.crm.settings.domain.Contacts;
import com.crm.settings.domain.ContactsActivityRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactsDao {
    int insert(Contacts contacts);
    int insertBund(ContactsActivityRelation relation);

    List<Contacts> selectListByName(@Param(value = "name") String name);
}
