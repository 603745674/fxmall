package com.qianfeng.fxmall;

import com.qianfeng.fxmall.commons.exception.PasswordErrorException;
import com.qianfeng.fxmall.commons.utils.MD5Utils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import com.qianfeng.fxmall.users.po.UserInfo;
import com.qianfeng.fxmall.users.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class SpringTest {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserService userService;
    @Test
    public void testCase1() {
        List<WxbGood> wxbGoods = goodsMapper.queryAllGoods();
       // wxbGoods.forEach((p) -> System.out.println(p));
    }
    @Test
    public void testMd5(){
        String md5 = MD5Utils.md5("123456", "LL");
    }

    @Test
    public void login(){
        UserInfo userInfo=null;
        try {
         //s    userInfo = userService.login("123", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
            System.out.println(userInfo);
    }
}