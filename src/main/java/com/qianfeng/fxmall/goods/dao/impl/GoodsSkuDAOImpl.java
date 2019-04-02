package com.qianfeng.fxmall.goods.dao.impl;

import com.qianfeng.fxmall.goods.bean.WxbGoodSku;
import com.qianfeng.fxmall.goods.dao.IGoodsSkuDAO;
import com.qianfeng.fxmall.goods.mapper.GoodsSkuMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GoodsSkuDAOImpl implements IGoodsSkuDAO {
    @Autowired
    private SqlSession session;

    @Override
    public void insertGoodSku(WxbGoodSku wxbGoodSku) {
        //SqlSession session = MyBatisSessionFactoryUtils.getSession();
        GoodsSkuMapper mapper = session.getMapper(GoodsSkuMapper.class);
        mapper.insertGoodSku(wxbGoodSku);
        session.commit();
    }
}
