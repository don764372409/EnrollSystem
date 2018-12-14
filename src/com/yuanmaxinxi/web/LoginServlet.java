package com.yuanmaxinxi.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.service.AdminService;
import com.yuanmaxinxi.util.CodeUtil;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	@Override
	public void init() throws ServletException {
		adminService = new AdminService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("login".equals(cmd)) {
			ResultDTO dto;
			//接收验证码参数
			String code = req.getParameter("code");
			//做非空判断
//			if (StringUtil.isNullOrEmpty(code)) {
//				dto = ResultDTO.newInstance(false, "请输入验证码.");
//				putJson(dto, resp);
//				return;
//			}
			//从session中获取刚才放进的验证码
			String sysCode = (String)req.getSession().getAttribute("code");
			//忽略大小写比较 用户输入的验证码 和 系统生成验证码
//			if (!sysCode.equalsIgnoreCase(code)) {
//				dto = ResultDTO.newInstance(false, "验证码输入错误.");
//				putJson(dto, resp);
//				return;
//			}
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			try {
				Admin admin = new Admin();
				admin.setUsername(username);
				admin.setPassword(password);
				//获取ip
				String ip = req.getRemoteAddr().toString();
				admin.setIp(ip);
				Admin loginAdmin = adminService.login(admin);
				//把登陆者放进作用域
				req.getSession().setAttribute("loginAdmin", loginAdmin);
				dto = ResultDTO.newInstance(true,"登录成功!");
			} catch (Exception e) {
				dto = ResultDTO.newInstance(false, e.getMessage());
				e.printStackTrace();
			}
			putJson(dto, resp);
		}else if("showCode".equals(cmd)) {
			//得到验证码
			Map<String, Object> map = CodeUtil.generateCodeAndPic();
			String code= (String)map.get("code");
			BufferedImage codeImg = (BufferedImage)map.get("codePic");
			//将验证码放入作用域
			req.getSession().setAttribute("code", code);
			//将图片输出
			ServletOutputStream out = resp.getOutputStream();
			ImageIO.write(codeImg, "jpeg", out);
		}else {
			//响应页面
			resp.sendRedirect("/login.jsp");
		}
	}
}
