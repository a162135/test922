package com.crm.settings.service.impl;

import com.crm.settings.dao.ClueDao;
import com.crm.settings.domain.Activity;
import com.crm.settings.domain.Clue;
import com.crm.settings.domain.ClueActivityRelation;
import com.crm.settings.service.ClueService;
import com.crm.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    @Resource
    private ClueDao dao;

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
}
