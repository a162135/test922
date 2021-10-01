package com.crm.settings.web.controller;

import com.crm.settings.domain.*;
import com.crm.settings.service.TranService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
