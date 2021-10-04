package com.crm.settings.dao;

import com.crm.settings.domain.Tran;
import com.crm.settings.domain.TranHistory;

import java.util.List;
import java.util.Map;

public interface TranDao {
    int insert(Tran tran);
    int insertHistory(TranHistory history);
    Tran selectAllById(Tran tran);
    List<TranHistory> selectHistoryByTranId(TranHistory history);

    int updateStageById(Tran tran);

    List<Map<String, Object>> selectStageGroup();
}
