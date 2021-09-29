package com.crm.settings.service;

import com.crm.settings.domain.Activity;
import com.crm.settings.domain.Clue;

import java.util.List;

public interface ClueService {
    int addClue(Clue clue);
    int setBund(String id,String[] activityId);
    List<Activity> queryBund(String id);
}
