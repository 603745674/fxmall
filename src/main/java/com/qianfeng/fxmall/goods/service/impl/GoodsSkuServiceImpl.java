package com.qianfeng.fxmall.goods.service.impl;


import com.qianfeng.fxmall.goods.bean.WxbGoodSku;
import com.qianfeng.fxmall.goods.mapper.GoodsSkuMapper;
import com.qianfeng.fxmall.goods.service.IGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsSkuServiceImpl implements IGoodsSkuService {
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Override
    public void insertGoodSku(WxbGoodSku wxbGoodSku) {
        goodsSkuMapper.insertGoodSku(wxbGoodSku);
    }
}
