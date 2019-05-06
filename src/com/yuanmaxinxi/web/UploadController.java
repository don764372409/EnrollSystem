package com.yuanmaxinxi.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.service.UploadService;

@CrossOrigin
@Controller
@RequestMapping("/upload")
public class UploadController {
	@Autowired
	private UploadService uploadService;

	/**
	 * 上传
	 * 
	 * @param file
	 * @param req
	 * @param type 上传类型,head为头像上传,validate为身份验证图片上传,product为商品图片上传
	 * @return
	 */
	@RequestMapping("/on")
	public @ResponseBody ResultDTO upload(MultipartFile file, HttpServletRequest req) {
		ResultDTO dto;
		try {
			HashMap<Object, Object> hashMap = new HashMap<>();
			hashMap.put("src", uploadService.uplaod(file, req, false));
			dto = ResultDTO.putSuccessObj("上传成功!", hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			dto = ResultDTO.putError(e.getMessage());
		}
		// 拼接前台web路劲
		return dto;
	}

	/**
	 * 上传
	 * 
	 * @param file
	 * @param req
	 * @return
	 */
	@RequestMapping("/ontohtml")
	public @ResponseBody ResultDTO uploadtohtml(MultipartFile file, HttpServletRequest req) {
		ResultDTO dto;
		try {
			HashMap<Object, Object> hashMap = new HashMap<>();
			hashMap.put("src", uploadService.uplaod(file, req, true));
			dto = ResultDTO.putSuccessObj("上传成功!", hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			dto = ResultDTO.putError(e.getMessage());
		}
		// 拼接前台web路劲
		return dto;
	}
}
