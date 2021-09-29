package com.crm.settings.dao;

import com.crm.settings.domain.Activity;
import com.crm.settings.domain.Clue;
import com.crm.settings.domain.ClueActivityRelation;

import java.util.List;

public interface ClueDao{
    int insert(Clue clue);
    int insertBund(ClueActivityRelation relation);
    List<Activity> selectActivity(ClueActivityRelation relation);
}
