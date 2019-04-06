package com.qianfeng.fxmall.commons.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class ApplicationContextUtils {
/*    private static ClassPathXmlApplicationContext applicationContext;

    public static void init(){
        applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }
    public static ClassPathXmlApplicationContext getApplicationContext() {
        return applicationContext;
    }*/

    private static WebApplicationContext applicationContext;

    public static WebApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(WebApplicationContext applicationContext) {
        ApplicationContextUtils.applicationContext = applicationContext;
    }


}
