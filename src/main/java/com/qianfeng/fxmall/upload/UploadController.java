/*
package com.qianfeng.fxmall.upload;


import com.qianfeng.fxmall.users.vo.JsonResultVO;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadController {


    @ResponseBody
    @PostMapping("/upload")
    public static JsonResultVO upload(MultipartFile uploadFile) throws Exception {
        System.out.println("ssss");
        System.out.println("uploadFile" + uploadFile);
        JsonResultVO jsonResultVO = new JsonResultVO();
        String imageFileName = uploadFile.getOriginalFilename();
        String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
        System.out.println(suffix);
        System.out.println("imageFileName" + imageFileName);
        FileOutputStream fileOutputStream = null;
        System.out.println("-------");
        long timeMills = System.currentTimeMillis();
        try {
            FTPClient ftp = new FTPClient();
            ftp.connect("192.168.53.20", 21);
            boolean login = ftp.login("anonymous", "@qq.com");
            System.out.println(login);
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
        return jsonResultVO;

    }

}
*/
