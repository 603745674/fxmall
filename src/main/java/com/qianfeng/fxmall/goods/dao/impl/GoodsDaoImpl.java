package com.qianfeng.fxmall.goods.dao.impl;

import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.commons.mybatis.MyBatisSessionFactoryUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.dao.IGoodsDao;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import com.qianfeng.fxmall.goods.mapper.GoodsSkuMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
/**
 * MyBatis的商品数据访问层
 *
 * 注意：
 * 异常在dao层不要捕获
 */
public class GoodsDaoImpl implements IGoodsDao {
    @Override
    public List<WxbGood> queryAllGoods() {
        GoodsMapper goodsMapper = MyBatisSessionFactoryUtils.getSession().getMapper(GoodsMapper.class);
        List<WxbGood> wxbGoods = goodsMapper.queryAllGoods();
        return wxbGoods;
    }

    @Override
    public void insertGoods(WxbGood wxbGood) {
        SqlSession session = MyBatisSessionFactoryUtils.getSession();
        GoodsMapper mapper = session.getMapper(GoodsMapper.class);
        mapper.insertGoods(wxbGood);
        session.commit();
    }


    @Override
    public List<WxbGood> queryGoodsByPage(Integer page) {
        GoodsMapper goodsMapper = MyBatisSessionFactoryUtils.getSession().getMapper(GoodsMapper.class);
        List<WxbGood> wxbGoods = goodsMapper.queryGoodsByPage(page, SystemConstantsUtils.Page.PAGE_SIZE);
        return wxbGoods;
    }
}
