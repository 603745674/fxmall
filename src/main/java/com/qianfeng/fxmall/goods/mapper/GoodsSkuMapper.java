package com.qianfeng.fxmall.goods.mapper;

import com.qianfeng.fxmall.goods.bean.WxbGoodSku;
import org.apache.ibatis.annotations.Param;

public interface GoodsSkuMapper {
    void insertGoodSku(@Param("skuId") String skuId,@Param("skuName") String skuName,@Param("skuCost") String skuCost,@Param("skuPrice") String skuPrice,@Param("skuPmoney") String skuPmoney,@Param("goodId") String goodId,@Param("serviceMoney") String serviceMoney);
}
