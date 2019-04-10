package com.qianfeng.fxmall.goods.dao;

import com.qianfeng.fxmall.goods.bean.WxbGood;

import java.util.List;

public interface IGoodsDao {
    List<WxbGood> queryGoodsByPage(Integer page);
    List<WxbGood> queryAllGoods();
    void insertGoods(WxbGood wxbGood);
    WxbGood queryGoodInfoById(String goodId);


}
