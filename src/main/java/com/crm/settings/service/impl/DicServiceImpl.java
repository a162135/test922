package com.crm.settings.service.impl;

import com.crm.settings.dao.DicDao;
import com.crm.settings.domain.DicValue;
import com.crm.settings.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class DicServiceImpl implements DicService {

    @Resource
    private DicDao dao;

    @Override
    public Map<String, List<DicValue>> getDic() {
        String[] typeCodes = dao.selectCount();
        Map<String,List<DicValue>> map = new TreeMap<>();
        List<DicValue> list;
        for (int i = 0; i < typeCodes.length; i++) {
            list = new ArrayList<>();
            list = dao.selectAllByTypeCode(typeCodes[i]);
            map.put(typeCodes[i],list);
        }
        return map;
    }
}
