package com.crm.settings.dao;

import com.crm.settings.domain.Tran;
import com.crm.settings.domain.TranHistory;

public interface TranDao {
    int insert(Tran tran);
    int insertHistory(TranHistory history);

}
