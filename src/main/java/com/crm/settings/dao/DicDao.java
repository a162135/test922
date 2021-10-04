package com.crm.settings.dao;

import com.crm.settings.domain.DicValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicDao {
    String[] selectCount();
    List<DicValue> selectAllByTypeCode(String typeCode);
    List<DicValue> selectStage();

    String selectIdByValue(@Param(value = "value") String value);
}
