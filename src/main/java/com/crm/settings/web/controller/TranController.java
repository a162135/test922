package com.crm.settings.web.controller;

import com.crm.settings.domain.*;
import com.crm.settings.service.TranService;
import com.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class TranController {

    @Resource
    private TranService service;

    @RequestMapping(value = "/tran/getOwner.do")
    @ResponseBody
    public List<User> getOwner(){
        return service.getOwner();
    }

    @RequestMapping(value = "/tran/addTran.do")
    @ResponseBody
    public Map<String,Object> addTran(Tran tran,String customerName){
        Customer customer = null;
        if (tran.getCustomerId() == null || "".equals(tran.getCustomerId())){
            customer = new Customer();
            customer.setName(customerName);
        }
        int i = service.addTran(tran,customer);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/tran/queryActivity.do")
    @ResponseBody
    public List<Activity> queryActivity(String name){
        return service.queryActivity(name);
    }

    @RequestMapping(value = "/tran/queryContacts.do")
    @ResponseBody
    public List<Contacts> queryContacts(String name){
        return service.queryContacts(name);
    }

    @RequestMapping(value = "/tran/queryAccount.do")
    @ResponseBody
    public Customer queryAccount(String name){
        return service.queryAccount(name);
    }

    @RequestMapping(value = "/tran/queryAllById.do")
    @ResponseBody
    public Tran queryAllById(Tran tran){
        Tran tran1 = service.queryAllById(tran);
        return tran1;
    }

    @RequestMapping(value = "/tran/queryHistory.do")
    @ResponseBody
    public List<TranHistory> queryHistory(TranHistory history){
        List<TranHistory> list = service.queryHistory(history);
        return list;
    }

    @RequestMapping(value = "/tran/queryStage.do")
    @ResponseBody
    public List<DicValue> queryStage(){
        List<DicValue> list = service.queryStage();
        return list;
    }

    @RequestMapping(value = "/tran/setStage.do")
    @ResponseBody
    public Map<String,Object> setStage(Tran tran,TranHistory history){
        tran.setEditTime(DateTimeUtil.getSysTime());
        int i = service.setStage(tran,history);
        Map<String,Object> map = new HashMap<>();
        if (i > 0){
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/tran/queryStageCount.do")
    @ResponseBody
    public List<Map<String,Object>> queryStageCount(){
        List<Map<String,Object>> list = service.queryStageCount();
        return list;
    }
}
