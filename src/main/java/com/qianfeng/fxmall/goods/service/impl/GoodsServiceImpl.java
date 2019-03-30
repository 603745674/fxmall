package com.qianfeng.fxmall.goods.service.impl;

import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.dao.IGoodsDao;
import com.qianfeng.fxmall.goods.dao.impl.GoodsDaoImpl;
import com.qianfeng.fxmall.goods.service.IGoodsService;

import java.util.List;

public class GoodsServiceImpl implements IGoodsService {
    private IGoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public void insertGoods(WxbGood wxbGood) {
        goodsDao.insertGoods(wxbGood);
    }

    @Override
    public List<WxbGood> queryGoodsByPage(Integer page){
        if(page<1){
            throw  new IndexOutOfBoundsException("页码不能小于1");
        }
        //计算起始下标
        int index = (page-1)* SystemConstantsUtils.Page.PAGE_SIZE;
        List<WxbGood> goods = goodsDao.queryGoodsByPage(index);
        return goods;
    }

    @Override
    public List<WxbGood> queryAllGoods() {
        List<WxbGood> goods = goodsDao.queryAllGoods();
        return goods;
    }
}
