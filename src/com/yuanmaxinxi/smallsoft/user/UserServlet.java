package com.yuanmaxinxi.smallsoft.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UserService;
import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/soft/user")
public class UserServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("login".equals(cmd)) {
			//查看是否授权
			//获取openID
			String openid = req.getParameter("openid");
			//根据openid获取user对象
			User user = userService.selectOneByOpenid(openid);
			ResultDTO dto;
			//用户在之前已经授权
			if (user!=null) {
				dto = ResultDTO.newInstance(true, "");
				dto.setObj(user);
			}else {
				//用户这是第一次使用这个小程序
				dto = ResultDTO.newInstance(false, "您还没有授权,点击确定跳转授权页面.");
			}
			//响应
			putJson(dto, resp);
		}else if("regist".equals(cmd)) {
			//前端授权之后的保存
			String name = req.getParameter("name");
			String url = req.getParameter("url");
			String openid = req.getParameter("openid");
			User user = new User();
			user.setName(name);
			user.setOpenid(openid);
			user.setUrl(url);
			ResultDTO dto;
			try {
				userService.regist(user);
				dto = ResultDTO.newInstance(true, "授权成功,点击进入下一步");
			} catch (Exception e) {
				if (e.getMessage().contains("授权成功")) {
					dto = ResultDTO.newInstance(true, e.getMessage());
				}else {
					dto = ResultDTO.newInstance(false, e.getMessage());
				}
			}
			putJson(dto, resp);
		}else if("code".equals(cmd)) {
			//拿传过来的数据
			String openid = req.getParameter("openid");
			//code是邀请人的邀请码  
			String code = req.getParameter("code");
			ResultDTO dto;
			try {
				userService.bindNumber(openid,code);
				dto = ResultDTO.newInstance(true, "绑定成功!点击跳转到首页");
			} catch (Exception e) {
				if (e.getMessage().contains("已经绑定")) {
					dto = ResultDTO.newInstance(true, e.getMessage());
				}else {
					dto = ResultDTO.newInstance(false, e.getMessage());
				}
			}
			putJson(dto, resp);
		}else if("shoucangs".equals(cmd)) {
			String id = req.getParameter("id");
			//获取收藏院校数量
			int numbers = userService.selectShoucangNumbers(id);
			putJson(numbers, resp);
		}
	}
}
