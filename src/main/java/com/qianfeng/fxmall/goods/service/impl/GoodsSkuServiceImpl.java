package com.qianfeng.fxmall.goods.service.impl;


import com.qianfeng.fxmall.goods.bean.WxbGoodSku;
import com.qianfeng.fxmall.goods.dao.IGoodsSkuDAO;
import com.qianfeng.fxmall.goods.service.IGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsSkuServiceImpl implements IGoodsSkuService {
    @Autowired
    private IGoodsSkuDAO goodsSkuDao;
    @Override
    public void insertGoodSku(WxbGoodSku wxbGoodSku) {
        goodsSkuDao.insertGoodSku(wxbGoodSku);
    }
}
