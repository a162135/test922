package com.crm.settings.dao;

import com.crm.settings.domain.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerDao {
    int insert(Customer customer);

    Customer selectByName(@Param(value = "name") String name);
}
