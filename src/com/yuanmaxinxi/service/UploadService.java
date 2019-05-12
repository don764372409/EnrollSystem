package com.yuanmaxinxi.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yuanmaxinxi.util.StringUtil;

@Service
public class UploadService {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String uplaod(MultipartFile file, HttpServletRequest req, boolean toHtml) {
		if (file == null) {
			throw new RuntimeException("上传文件失败.errorCode:2001");
		}
		String fileName, type;
		String host = StringUtil.getHost(req);
		Date date = new Date();
		String time = sdf.format(date);
		Long stamp = date.getTime();
		try {
			type = StringUtil.getEndFix(file.getOriginalFilename());
			fileName = stamp + "." + type;
			 String path = req.getServletContext().getRealPath("/commons/img/"+time+"/");
			// 创建一个空文件
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				File parentFile = targetFile.getParentFile();
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				targetFile.createNewFile();
			}
			// 写文件
			file.transferTo(targetFile);
			return host + "/commons/img/" + time + "/" + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("上传文件失败.errorCode:2003");
		}
	}
}
