package com.qianfeng.fxmall.goods.service.impl;


import com.qianfeng.fxmall.commons.info.Constants;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import com.qianfeng.fxmall.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public void insertGoods(WxbGood wxbGood) {
        goodsMapper.insertGoods(wxbGood);
    }

    @Override
    public WxbGood queryGoodInfoById(String goodId) {
        WxbGood good = goodsMapper.queryGoodInfoById(goodId);
        return good;
    }

    @Override
    public List<WxbGood> queryGoodsByPage(Integer page){
        if(page<1){
            throw  new IndexOutOfBoundsException("页码不能小于1");
        }
        //计算起始下标
        int index = (page-1)* Constants.Page.PAGE_SIZE;
        List<WxbGood> goods = goodsMapper.queryGoodsByPage(index,Constants.Page.PAGE_SIZE);
        return goods;
    }

    @Override
    public List<WxbGood> queryAllGoods() {
        List<WxbGood> goods = goodsMapper.queryAllGoods();
        return goods;
    }
}
