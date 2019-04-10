package com.qianfeng.fxmall.goods.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.fxmall.goods.service.IGoodsService;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseServlet extends HttpServlet{
	//private Logger logger = Logger.getLogger(BaseServlet.class);
    private static IGoodsService goodsService;

/*    protected static ClassPathXmlApplicationContext applicationContext;

    static {
        System.out.println(">>>>>>>初始化Spring容器>>>>>>>>>>>");
        applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //获得方法名
        String m = req.getParameter("m");
       // logger.info("执行了方法:"+m);
        //通过反射获得方法
        try {
            Method method = getClass().getMethod(m, HttpServletRequest.class,HttpServletResponse.class);
            //logger.info("开始方法:"+m);
            //调用方法
            method.invoke(this,req, resp);
           // logger.info("结束方法:"+m);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
