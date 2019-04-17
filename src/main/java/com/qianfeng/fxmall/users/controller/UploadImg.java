package com.qianfeng.fxmall.users.controller;


import com.qianfeng.fxmall.commons.utils.ImgMD5;
import com.qianfeng.fxmall.users.po.TbImages;
import com.qianfeng.fxmall.users.service.ImgService;
import com.qianfeng.fxmall.users.vo.JsonResultVO;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadImg {
    @Autowired
    public ImgService imgService;
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile) throws Exception {
        System.out.println("imgService:"+imgService);
        JsonResultVO jsonResultVO = new JsonResultVO();
        String imageFileName = uploadFile.getOriginalFilename();
        String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
        FileOutputStream fileOutputStream = null;
        //获取上传文件的MD5值
        byte[] bytes = uploadFile.getBytes();
        String md5 = ImgMD5.md5(bytes);
        TbImages tbImages = imgService.queryImgByMD5(md5);
        if(null==tbImages){
            long timeMills = System.currentTimeMillis();
            try {
                FTPClient ftp = new FTPClient();
                ftp.connect("192.168.53.20", 21);
                boolean login = ftp.login("anonymous", "@qq.com");

                String imagePath = "http://localhost/images/";

                int replyCode = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(replyCode)) {
                    System.out.println("获取响应失败");
                    return null;
                }
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftp.makeDirectory("images");
                ftp.changeWorkingDirectory("images");
                InputStream inputStream = uploadFile.getInputStream();
                ftp.storeFile(timeMills + "LL." + suffix, inputStream);
                ftp.logout();
                jsonResultVO.setCode(1);
                jsonResultVO.setMsg(imagePath + timeMills + "LL." + suffix);
                TbImages tbImages1 = new TbImages();
                tbImages1.setImgMd5(md5);
                tbImages1.setImgUrl(jsonResultVO.getMsg());
                imgService.insertImg(tbImages1);
            } catch (IOException e) {
                e.printStackTrace();
                jsonResultVO.setCode(0);
                jsonResultVO.setMsg("上传失败");
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            jsonResultVO.setCode(1);
            jsonResultVO.setMsg(tbImages.getImgUrl());
        }

        return jsonResultVO.getMsg();

    }
}
