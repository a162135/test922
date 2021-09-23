package com.crm.settings.web.handler;

import com.crm.settings.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Object loginException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("success",false);
        map.put("msg",e.getMessage());
        return map;
    }

    @ExceptionHandler
    public String exception(Exception e){
        System.out.println(e.getMessage());
        return "forward:/static/index.html";
    }
}
