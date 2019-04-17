package com.qianfeng.fxmall.users.mapper;

import com.qianfeng.fxmall.users.po.TbImages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ImgMapper {
    TbImages queryImgByMD5(@Param("imgMd5") String md5);
    void insertImg(TbImages tbImages);
}
