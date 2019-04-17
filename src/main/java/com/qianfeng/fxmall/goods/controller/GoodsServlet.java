package com.qianfeng.fxmall.goods.controller;


import com.qianfeng.fxmall.commons.info.Constants;
import com.qianfeng.fxmall.commons.utils.ApplicationContextUtils;
import com.qianfeng.fxmall.commons.utils.ImgMD5;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.service.IGoodsService;

import com.qianfeng.fxmall.users.po.TbImages;
import com.qianfeng.fxmall.users.service.IUpload;
import com.qianfeng.fxmall.users.vo.JsonResultVO;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class GoodsServlet extends BaseServlet {
    // ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
    //private IGoodsService goodsService = classPathXmlApplicationContext.getBean(GoodsServiceImpl.class);
    private IGoodsService goodsService = (IGoodsService) ApplicationContextUtils.getApplicationContext().getBean("goodsServiceImpl");
    //private static Logger logger = Logger.getLogger(GoodsServlet.class);
    private IUpload iUpload = (IUpload) ApplicationContextUtils.getApplicationContext().getBean("imgService");


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void AllGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        try {
            pageStr = pageStr == null ? "1" : pageStr;
            List<WxbGood> goodList = goodsService.queryGoodsByPage(Integer.parseInt(pageStr));
            List<WxbGood> allGoods = goodsService.queryAllGoods();
            int pageCount = allGoods.size();
            int size = Constants.Page.PAGE_SIZE;
            int pages = 0;
            if (pageCount % size == 0) {
                pages = pageCount / size;
            }
            if (pageCount % size != 0) {
                pages = pageCount / size + 1;
            }
            req.setAttribute("pages", pages);
            req.setAttribute("goodsList", goodList);
            req.setAttribute("pageCount", pageCount);
            req.setAttribute("size", size);
            req.setAttribute("page", pageStr);
            req.getRequestDispatcher("goods_list.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //能处理就处理
            //不能处理就跳转到错误提示页面
        }


    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WxbGood wxbGood = new WxbGood();
        String carImg = "";
        String goodId = String.valueOf(UUID.randomUUID().hashCode()).substring(1,9);
        wxbGood.setGoodId(goodId);
        wxbGood.setSkuCost("sss");
        wxbGood.setSkuTitle("ssss");
        wxbGood.setSkuCost("sss");
        wxbGood.setSkuPrice("ssss");
        wxbGood.setSkuPmoney("ssss");
        wxbGood.setState(10);
        Date date = new Date();
        java.sql.Timestamp dateTime = new java.sql.Timestamp(date.getTime());
        wxbGood.setCreateTime(dateTime);
        wxbGood.setToped(10);
        wxbGood.setRecomed(10);
        //判断请求中是否有上传内容
        if (ServletFileUpload.isMultipartContent(req)) {
            //创建文件上传对象
            ServletFileUpload upload = new ServletFileUpload();
            //设置文件最大总大小
            upload.setSizeMax(Constants.MAX_SIZE);
            //设置每个文件最大大小
            upload.setFileSizeMax(Constants.FILE_MAX_SIZE);
            try {
                //获得表单项的迭代器
                FileItemIterator itr = upload.getItemIterator(req);
                //遍历迭代器
                int i = 0;
                while (itr.hasNext()) {
                    //获得每个表单项
                    FileItemStream item = itr.next();
                    //判断是否是一般的表单项
                    if (item.isFormField()) {
                        //读取表单项的值
                        String value = Streams.asString(item.openStream(), "UTF-8");
                        //判断表单项的名称
                        switch (item.getFieldName()) {
                            case "good_name":
                                wxbGood.setGoodName(value);
                                break;
                            case "type_id":
                                wxbGood.setTypeId((value));
                                break;
                            case "order_no":
                                wxbGood.setOrderNo(Long.parseLong(value));
                                break;
                            case "sell_num":
                                wxbGood.setSellNum(Long.parseLong(value));
                                break;
                            case "promote_desc":
                                wxbGood.setPromoteDesc((value));
                                break;
                            case "tags":
                                wxbGood.setTags((value));
                                break;
                            case "copy_desc":
                                wxbGood.setCopyDesc((value));
                                break;
                            case "forward_link":
                                wxbGood.setForwardLink((value));
                                break;
                        }
                    } else {
                        String filename = item.getName();
                        if (!"".equals(filename)) {
                            //处理文件域
                            switch (item.getFieldName()) {
                                case "uploadGood":
                                    carImg = filename;
                                    MultipartFile mfile = new MockMultipartFile("copy"+filename,filename, ContentType.APPLICATION_OCTET_STREAM.toString(),item.openStream());

                                    if(i==0){
                                       // wxbGood.setGoodPic(SaveFile.saveFile(item.openStream(), carImg));
                                        try {
                                            wxbGood.setGoodPic(upload(mfile));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if(i==1){
                                       // wxbGood.setGoodPic1(SaveFile.saveFile(item.openStream(), carImg));
                                        try {
                                            wxbGood.setGoodPic1(upload(mfile));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if(i==2){
                                       // wxbGood.setGoodPic2(SaveFile.saveFile(item.openStream(), carImg));
                                        try {
                                            wxbGood.setGoodPic2(upload(mfile));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    i++;
                                    break;
                            }
                            //保存文件
                        }
                    }
                }
                goodsService.insertGoods(wxbGood);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("goods.do?m=AllGoods").forward(req, resp);
        }

    }
    public String upload(MultipartFile uploadFile) throws Exception {

        JsonResultVO jsonResultVO = new JsonResultVO();
        String imageFileName = uploadFile.getOriginalFilename();
        String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
        FileOutputStream fileOutputStream = null;

        byte[] bytes = uploadFile.getBytes();
        String md5 = ImgMD5.md5(bytes);
        System.out.println("文件的MD5值："+md5);

        try {
            //获取数据库是否有相同的MD5文件
            TbImages tbImages = iUpload.queryImgByMD5(md5);
            //如果数据库没有这个文件，就将文件信息存入数据库，并且完成FTP上传文件
            if (null == tbImages) {

            long timeMills = System.currentTimeMillis();
            try {
                FTPClient ftp = new FTPClient();
                ftp.connect("192.168.53.20", 21);
                boolean login = ftp.login("anonymous", "@qq.com");

                String imagePath = "http://192.168.53.20/images/";

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
                //第一次上传图片完毕之后，需要将图片写入数据库
                TbImages tbImagesPO = new TbImages();
                tbImagesPO.setImgMd5(md5);
                tbImagesPO.setImgUrl(imagePath + timeMills + "LL." + suffix);
                iUpload.insertImg(tbImagesPO);
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
            } else {
                //如果数据库中有这一张图片，则将存在的图片信息返回给用户
                jsonResultVO.setCode(1);
                jsonResultVO.setMsg(tbImages.getImgUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResultVO.getMsg();

    }



    public void queryGoodInfoById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodId = req.getParameter("goodId");
        WxbGood good = goodsService.queryGoodInfoById(goodId);
        req.getSession().setAttribute("good",good);
        req.getRequestDispatcher("goodsInfoList.jsp").forward(req,resp);
    }


}
