package com.qianfeng.fxmall;

import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.dao.IGoodsDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        IGoodsDao bean = classPathXmlApplicationContext.getBean(IGoodsDao.class);
        List<WxbGood> goodList = bean.queryAllGoods();
        goodList.forEach((p)-> System.out.println(p));
    }

}
