package com.qianfeng.fxmall.goods.mapper;

import com.qianfeng.fxmall.goods.bean.WxbGoodSku;
import org.apache.ibatis.annotations.Param;

public interface GoodsSkuMapper {
    void insertGoodSku(WxbGoodSku wxbGoodSku);
}
