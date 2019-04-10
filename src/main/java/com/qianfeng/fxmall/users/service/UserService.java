package com.qianfeng.fxmall.users.service;

import com.qianfeng.fxmall.commons.exception.PasswordErrorException;
import com.qianfeng.fxmall.commons.exception.UsernameNotFoundException;
import com.qianfeng.fxmall.commons.utils.MD5Utils;
import com.qianfeng.fxmall.users.mapper.UserMapper;
import com.qianfeng.fxmall.users.po.UserInfo;
import com.qianfeng.fxmall.users.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserInfo login(UserLoginVO userLoginVO) throws Exception{
        if(userLoginVO == null){
            throw new NullPointerException("param is null");
        }
        String username=userLoginVO.getUsername();
        String password=userLoginVO.getPassword();
        if(null==username||"".equals(username)){
            throw new NullPointerException("username is null");
        }
        UserInfo userInfo = userMapper.checkUserName(username);
        if(userInfo==null){
            throw new UsernameNotFoundException();
        }

        String md5Username = MD5Utils.md5(password,"LL");
        if(!md5Username.equals(userInfo.getPassword())){
            throw new PasswordErrorException("password is error");
        }
        return userInfo;
    }
}
