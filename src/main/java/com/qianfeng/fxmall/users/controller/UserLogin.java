package com.qianfeng.fxmall.users.controller;

import com.qianfeng.fxmall.commons.exception.PasswordErrorException;
import com.qianfeng.fxmall.commons.exception.UsernameNotFoundException;
import com.qianfeng.fxmall.users.po.UserInfo;
import com.qianfeng.fxmall.users.service.UserService;
import com.qianfeng.fxmall.users.vo.JsonResultVO;
import com.qianfeng.fxmall.users.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class UserLogin {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public String login(UserLoginVO userLoginVO){
        UserInfo userInfo = null;
        try {
            userInfo = userService.login(userLoginVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(userInfo!=null){
            return "goods.do?m=AllGoods";
        }
        return null;
    }
    @ResponseBody
    @PostMapping("/userlogin")
    public JsonResultVO loginAjax(UserLoginVO userLoginVO, HttpSession session){
        JsonResultVO jsonResultVO = new JsonResultVO();
        try {
            UserInfo userInfo = userService.login(userLoginVO);
            session.setAttribute("userInfo",userInfo);
            jsonResultVO.setCode(1);
            jsonResultVO.setMsg("登陆成功");
        } catch (NullPointerException e) {
            e.printStackTrace();
            jsonResultVO.setCode(0);
            jsonResultVO.setMsg("请输入用户名!!!");
        }catch (UsernameNotFoundException|PasswordErrorException e){
            e.printStackTrace();
            jsonResultVO.setCode(0);
            jsonResultVO.setMsg("用户名或密码错误");
        } catch (Exception e){
            e.printStackTrace();
            jsonResultVO.setCode(0);
            jsonResultVO.setMsg("请联系管理员603745674");
        }
        return jsonResultVO;
    }
}
