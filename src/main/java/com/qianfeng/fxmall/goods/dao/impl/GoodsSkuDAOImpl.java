package com.qianfeng.fxmall.goods.dao.impl;

import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.commons.mybatis.MyBatisSessionFactoryUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.bean.WxbGoodSku;
import com.qianfeng.fxmall.goods.dao.IGoodsSkuDAO;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import com.qianfeng.fxmall.goods.mapper.GoodsSkuMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GoodsSkuDAOImpl implements IGoodsSkuDAO {

    @Override
    public void insertGoodSku(WxbGoodSku wxbGoodSku) {
        SqlSession session = MyBatisSessionFactoryUtils.getSession();
        GoodsSkuMapper mapper = session.getMapper(GoodsSkuMapper.class);
        mapper.insertGoodSku(wxbGoodSku.getSkuId(),wxbGoodSku.getSkuName(),wxbGoodSku.getSkuCost(),wxbGoodSku.getSkuPrice(),wxbGoodSku.getSkuPmoney(),wxbGoodSku.getGoodId(),wxbGoodSku.getServiceMoney());
        session.commit();
    }
}
