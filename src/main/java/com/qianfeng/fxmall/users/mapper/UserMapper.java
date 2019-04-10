package com.qianfeng.fxmall.users.mapper;

import com.qianfeng.fxmall.users.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserInfo checkUserName(@Param("userName") String userName);
}
