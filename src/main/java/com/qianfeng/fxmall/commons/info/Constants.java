package com.qianfeng.fxmall.commons.info;

import com.qianfeng.fxmall.goods.service.IGoodsService;
import com.qianfeng.fxmall.goods.service.impl.GoodsServiceImpl;

public class Constants {
	public static final int PAGESIZE=10;
	public static final int MAX_SIZE = 50*1024*1024;
	public static final int FILE_MAX_SIZE = 10*1024*1024;
	public static final String UPLOAD_PATH = "c:/test/w/";
	public static final IGoodsService goodsService = new GoodsServiceImpl();
	/**
	 * 分页相关常亮
	 */
	public final static class Page{
		/**
		 * 页大小
		 */
		public static final Integer PAGE_SIZE=3;
	}
}
