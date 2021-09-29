package com.crm.settings.dao;

import com.crm.settings.domain.DicValue;

import java.util.List;

public interface DicDao {
    String[] selectCount();
    List<DicValue> selectAllByTypeCode(String typeCode);
}
