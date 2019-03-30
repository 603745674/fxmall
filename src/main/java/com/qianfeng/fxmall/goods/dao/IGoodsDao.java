package com.qianfeng.fxmall.goods.dao;

import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.bean.WxbGoodSku;

import java.util.List;

public interface IGoodsDao {
    List<WxbGood> queryGoodsByPage(Integer page);
    List<WxbGood> queryAllGoods();
    void insertGoods(WxbGood wxbGood);



}
