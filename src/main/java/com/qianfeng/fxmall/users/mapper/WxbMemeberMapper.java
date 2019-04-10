package com.qianfeng.fxmall.users.mapper;

import com.qianfeng.fxmall.users.po.WxbMemeber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface WxbMemeberMapper {
    WxbMemeber  checkWxbMemeberName(@Param("name") String name);
}
