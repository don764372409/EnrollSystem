package com.yuanmaxinxi.web.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/upload")
public class UploadServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultDTO dto;
		//1.获取磁盘工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2.创建ServletFileUpload对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//--------将网络路径转换为绝对路径-----------------
		String basePath = req.getServletContext().getRealPath("/upload/");
		//得到时间戳
		String fileName = new Date().getTime()+"";
		//3.解析请求对象
		try {
			List<FileItem> list = upload.parseRequest(req);
			//4.遍历
			for (FileItem item : list) {
				if (!item.isFormField()) {
					//得到文件内容
					byte[] bs = item.get();
					//得到文件名
					String name = item.getName();
					//获取后缀
					String endWidth = StringUtil.getEndWidth(name);
					//拼接最终的文件名
					fileName+=endWidth;
					File file = new File(basePath,fileName);
					FileOutputStream out = new FileOutputStream(file);
					out.write(bs);
					out.close();
				}
			}
			dto = ResultDTO.newInstance(true,"/upload/"+fileName);
		} catch (Exception e) {
			dto = ResultDTO.newInstance(false,"头像上传失败,请刷新重试");
			e.printStackTrace();
		}
		putJson(dto, resp);
	}
}
