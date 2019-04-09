package com.yuanmaxinxi.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.service.AdminService;
import com.yuanmaxinxi.service.EnrollService;
import com.yuanmaxinxi.util.CodeUtil;
import com.yuanmaxinxi.util.StringUtil;

@RequestMapping("/login")
@Controller
public class LoginController{
	@Autowired
	private AdminService adminService;
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginAdmin");
		return "login";
	}
	@RequestMapping("/")
	public String view(HttpSession session,HttpServletResponse response) {
		return "login";
	}
	@RequestMapping("/showCode")
	public void showCode(HttpSession session,HttpServletResponse response) {
		//得到验证码
		Map<String, Object> map = CodeUtil.generateCodeAndPic();
		String code= (String)map.get("code");
		BufferedImage codeImg = (BufferedImage)map.get("codePic");
		//将验证码放入作用域
		session.setAttribute("code", code);
		//将图片输出
		try {
			ServletOutputStream out = response.getOutputStream();
			ImageIO.write(codeImg, "jpeg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/login")
	@ResponseBody
	public ResultDTO login(Admin admin,String code,HttpSession session,HttpServletRequest request) {
		ResultDTO dto;
		//做非空判断
		if (StringUtil.isNullOrEmpty(code)) {
			dto = ResultDTO.newInstance(false, "请输入验证码.");
			return dto;
		}
		//从session中获取刚才放进的验证码
		String sysCode = (String)session.getAttribute("code");
		//忽略大小写比较 用户输入的验证码 和 系统生成验证码
		if (StringUtil.isNullOrEmpty(sysCode)||!sysCode.equalsIgnoreCase(code)) {
			dto = ResultDTO.newInstance(false, "验证码输入错误.");
			return dto;
		}
		try {
			//获取ip
			String ip = request.getRemoteAddr().toString();
			admin.setIp(ip);
			Admin loginAdmin = adminService.login(admin);
			//把登陆者放进作用域
			session.setAttribute("loginAdmin", loginAdmin);
			dto = ResultDTO.newInstance(true,"登录成功!");
		} catch (Exception e) {
			dto = ResultDTO.newInstance(false, e.getMessage());
			e.printStackTrace();
		}
		return dto;
	}
}
