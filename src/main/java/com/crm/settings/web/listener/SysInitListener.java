package com.crm.settings.web.listener;

import com.crm.settings.domain.DicValue;
import com.crm.settings.service.DicService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class SysInitListener implements ServletContextListener {

    private DicService service;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        service = (DicService) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext()).getBean("dicServiceImpl");
        Map<String,List<DicValue>> dic = service.getDic();
        servletContextEvent.getServletContext().setAttribute("dic",dic);

        ResourceBundle rb = ResourceBundle.getBundle("Stage2Possibility");
        Enumeration<String> e = rb.getKeys();
        Map<String,String> stage = new HashMap<>();
        while(e.hasMoreElements()){
            String key = e.nextElement();
            String value = rb.getString(key);
            stage.put(key,value);
        }
        servletContextEvent.getServletContext().setAttribute("stage",stage);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute("dic");
        servletContextEvent.getServletContext().removeAttribute("stage");
    }
}
