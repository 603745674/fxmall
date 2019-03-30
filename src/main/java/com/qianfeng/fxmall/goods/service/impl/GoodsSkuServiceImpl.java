package com.qianfeng.fxmall.goods.service.impl;

import com.qianfeng.fxmall.goods.bean.WxbGoodSku;
import com.qianfeng.fxmall.goods.dao.IGoodsDao;
import com.qianfeng.fxmall.goods.dao.IGoodsSkuDAO;
import com.qianfeng.fxmall.goods.dao.impl.GoodsDaoImpl;
import com.qianfeng.fxmall.goods.dao.impl.GoodsSkuDAOImpl;
import com.qianfeng.fxmall.goods.service.IGoodsSkuService;

public class GoodsSkuServiceImpl implements IGoodsSkuService {
    private IGoodsSkuDAO goodsSkuDao = new GoodsSkuDAOImpl();
    @Override
    public void insertGoodSku(WxbGoodSku wxbGoodSku) {
        goodsSkuDao.insertGoodSku(wxbGoodSku);
    }
}
