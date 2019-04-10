package com.qianfeng.fxmall.users.service;

import com.qianfeng.fxmall.commons.exception.PasswordErrorException;
import com.qianfeng.fxmall.commons.exception.UsernameNotFoundException;
import com.qianfeng.fxmall.commons.utils.MD5Utils;
import com.qianfeng.fxmall.users.mapper.WxbMemeberMapper;
import com.qianfeng.fxmall.users.po.WxbMemeber;
import com.qianfeng.fxmall.users.vo.WxbMemeberLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxbMemeberService {
    @Autowired
    private WxbMemeberMapper wxbMemeberMapper;
    public WxbMemeber login(WxbMemeberLoginVO wxbMemeberLoginVO) throws Exception{
        if(wxbMemeberLoginVO == null){
            throw new NullPointerException("param is null");
        }
        String username=wxbMemeberLoginVO.getName();
        String password=wxbMemeberLoginVO.getPassword();
        if(null==username||"".equals(username)){
            throw new NullPointerException("name is null");
        }
        WxbMemeber wxbMemeber = wxbMemeberMapper.checkWxbMemeberName(username);
        if(wxbMemeber==null){
           throw new UsernameNotFoundException();
        }

        String md5Username = MD5Utils.md5(password,"LL");
        if(!md5Username.equals(wxbMemeber.getPassword())){
            throw new PasswordErrorException("password is error");
        }
        return wxbMemeber;
    }
}
