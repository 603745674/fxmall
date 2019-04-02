package com.qianfeng.fxmall.goods.dao.impl;



import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.dao.IGoodsDao;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * MyBatis的商品数据访问层
 *
 * 注意：
 * 异常在dao层不要捕获
 */
@Component
public class GoodsDaoImpl implements IGoodsDao {
    @Autowired
    private SqlSession session;
    @Override
    public List<WxbGood> queryAllGoods() {
       // GoodsMapper goodsMapper = MyBatisSessionFactoryUtils.getSession().getMapper(GoodsMapper.class);
        GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
        List<WxbGood> wxbGoods = goodsMapper.queryAllGoods();
        return wxbGoods;
    }

    @Override
    public void insertGoods(WxbGood wxbGood) {
        //SqlSession session = MyBatisSessionFactoryUtils.getSession();
        GoodsMapper mapper = session.getMapper(GoodsMapper.class);
        mapper.insertGoods(wxbGood);
        session.commit();
    }


    @Override
    public List<WxbGood> queryGoodsByPage(Integer page) {
        //GoodsMapper goodsMapper = MyBatisSessionFactoryUtils.getSession().getMapper(GoodsMapper.class);
        GoodsMapper goodsMapper =session.getMapper(GoodsMapper.class);
        List<WxbGood> wxbGoods = goodsMapper.queryGoodsByPage(page, SystemConstantsUtils.Page.PAGE_SIZE);
        return wxbGoods;
    }
}
