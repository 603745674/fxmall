package com.qianfeng.fxmall.commons.info;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;


public class SaveFile {
	private static Logger logger = Logger.getLogger(SaveFile.class);
	//保存上传文件
	public static synchronized String saveFile(InputStream in,String filename) throws IOException{
		logger.info("保存文件:"+filename);
		//从文件中截取后缀名
		String suffix = filename.substring(filename.lastIndexOf("."));
//		产生唯一的文件名
		String name = UUID.randomUUID().toString()+suffix;
		filename = Constants.UPLOAD_PATH+name;
		//创建文件流
		FileOutputStream out = new FileOutputStream(filename);
		//读取上传的流,写入本地文件
		Streams.copy(in, out, true);
		logger.info("保存成功:"+filename);
		return name;
	}
}
