package com.qianfeng.fxmall.users.service;

import com.qianfeng.fxmall.users.mapper.ImgMapper;
import com.qianfeng.fxmall.users.po.TbImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImgService implements IUpload {
    @Autowired
    private ImgMapper imgMapper;
    @Override
    public TbImages queryImgByMD5(String md5){
        TbImages tbImages = imgMapper.queryImgByMD5(md5);
        return tbImages;
    }
    @Override
    public void insertImg(TbImages tbImages){
        imgMapper.insertImg(tbImages);
    }
}
