package com.qianfeng.fxmall.users.controller;


import com.qianfeng.fxmall.commons.exception.PasswordErrorException;
import com.qianfeng.fxmall.commons.exception.UsernameNotFoundException;
import com.qianfeng.fxmall.users.po.WxbMemeber;
import com.qianfeng.fxmall.users.service.WxbMemeberService;
import com.qianfeng.fxmall.users.vo.JsonResultVO;
import com.qianfeng.fxmall.users.vo.WxbMemeberLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
public class WxbMemeberLogin {
    @Autowired
    private WxbMemeberService wxbMemeberService;
    @ResponseBody
    @PostMapping("/WxbMemeberlogin")
    public JsonResultVO loginAjax(WxbMemeberLoginVO wxbMemeberLoginVO, HttpSession session){
        JsonResultVO jsonResultVO = new JsonResultVO();
        try {
            WxbMemeber wxbMemeber = wxbMemeberService.login(wxbMemeberLoginVO);
            session.setAttribute("userInfo",wxbMemeber);
            jsonResultVO.setCode(1);
            jsonResultVO.setMsg("登陆成功");
        } catch (NullPointerException e) {
            e.printStackTrace();
            jsonResultVO.setCode(0);
            jsonResultVO.setMsg("请输入用户名!!!");
        }catch (UsernameNotFoundException | PasswordErrorException e){
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
