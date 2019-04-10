package com.qianfeng.fxmall.goods.controller;


import com.qianfeng.fxmall.commons.info.Constants;
import com.qianfeng.fxmall.commons.info.SaveFile;
import com.qianfeng.fxmall.commons.utils.ApplicationContextUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.service.IGoodsService;
import com.qianfeng.fxmall.goods.service.impl.GoodsServiceImpl;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
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
        wxbGood.setGoodName("sss");
        wxbGood.setGoodPic("sss");
        wxbGood.setGoodPic1("sss");
        wxbGood.setGoodPic2("sss");
        wxbGood.setPromoteDesc("sss");
        wxbGood.setSkuCost("sss");
        wxbGood.setSkuTitle("ssss");
        wxbGood.setSkuCost("sss");
        wxbGood.setSkuPrice("ssss");
        wxbGood.setSkuPmoney("ssss");
        wxbGood.setTypeId("ssss");
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
                                   // logger.info("carImg1文件：" + filename);
                                    if(i==0){
                                        wxbGood.setGoodPic(SaveFile.saveFile(item.openStream(), carImg));
                                    }
                                    if(i==1){
                                        wxbGood.setGoodPic1(SaveFile.saveFile(item.openStream(), carImg));
                                    }
                                    if(i==2){
                                        wxbGood.setGoodPic2(SaveFile.saveFile(item.openStream(), carImg));
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

            //String goodId = UUID.randomUUID().toString().substring(0,8);
            //String goodId = String.valueOf(UUID.randomUUID().hashCode()).substring(1,9);
            //String goodName=req.getParameter("good_name");
            //String customerId=req.getParameter("customer_id");
           /* String goodPic=req.getParameter("good_pic");
            String goodPic1=req.getParameter("good_pic1");
            String goodPic2=req.getParameter("good_pic2");*/
            //String promoteDesc=req.getParameter("promote_desc");
            // String skuTitle=req.getParameter("sku_title");
           /* String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");
            String goodName=req.getParameter("good_name");*/

         /*   wxbGood.setGoodId(goodId);
            wxbGood.setGoodName(goodName);
            wxbGood.setCustomerId(customerId);
            wxbGood.setGoodPic("sss");
            wxbGood.setGoodPic1("sss");
            wxbGood.setGoodPic2("sss");
            wxbGood.setPromoteDesc(promoteDesc);
            wxbGood.setSkuCost("sss");
            wxbGood.setSkuTitle("ssss");
            wxbGood.setSkuCost("sss");
            wxbGood.setSkuPrice("ssss");
            wxbGood.setSkuPmoney("ssss");
            wxbGood.setTypeId("ssss");
            wxbGood.setState(10);
            Date date = new Date();
            java.sql.Timestamp dateTime = new java.sql.Timestamp(date.getTime());
            wxbGood.setCreateTime(dateTime);
            wxbGood.setToped(10);
            wxbGood.setRecomed(10);
            goodsService.insertGoods(wxbGood);*/
//
//            WxbGoodSku wxbGoodSku = new WxbGoodSku();
//            wxbGoodSku.setSkuId(String.valueOf(UUID.randomUUID().hashCode()).substring(1,9));
//            String skuName =  req.getParameter("sku_name");
//            String skuCost= req.getParameter("sku_cost");
//            String skuPrice=req.getParameter("sku_price");
//            String skuPmoney=req.getParameter("sku_pmoney");
//            String serviceMoney=req.getParameter("service_money");
//             wxbGoodSku.setSkuName(skuName);
//            wxbGoodSku.setSkuCost(skuCost);
//            wxbGoodSku.setSkuPrice(skuPrice);
//            wxbGoodSku.setSkuPmoney(skuPmoney);
//           wxbGoodSku.setGoodId(goodId);
//            wxbGoodSku.setServiceMoney(serviceMoney);
//            goodsSkuService.insertGoodSku(wxbGoodSku);

            req.getRequestDispatcher("goods.do?m=login").forward(req, resp);
        }

    }



    public void queryGoodInfoById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodId = req.getParameter("goodId");
        WxbGood good = goodsService.queryGoodInfoById(goodId);
        req.getSession().setAttribute("good",good);
        req.getRequestDispatcher("goodsInfoList.jsp").forward(req,resp);
    }
}
