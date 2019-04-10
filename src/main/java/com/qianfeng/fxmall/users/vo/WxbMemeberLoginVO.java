package com.qianfeng.fxmall.users.vo;

public class WxbMemeberLoginVO {
    private String name;
    private String password;

    public WxbMemeberLoginVO(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public WxbMemeberLoginVO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
