package com.qianfeng.fxmall;

import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
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

    @Test
    public void testCase1() {
        List<WxbGood> wxbGoods = goodsMapper.queryAllGoods();
        wxbGoods.forEach((p) -> System.out.println(p));
    }
}