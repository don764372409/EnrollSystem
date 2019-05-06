package com.yuanmaxinxi.smallsoft.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UserService;
@RequestMapping("/soft/user")
@Controller
public class UserController{
	@Autowired
	private UserService userService;
	@RequestMapping("/updateInfo")
	@ResponseBody
	public ResultDTO updateInfo (User user){
		try {
			userService.update(user);
			return ResultDTO.putError("修改信息成功.");
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	@RequestMapping("/login")
	@ResponseBody
	public ResultDTO login (String openid){
		//查看是否授权
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
		return dto;
	}
	@ResponseBody
	@RequestMapping("/regist")
	public ResultDTO regist(User user){
		//前端授权之后的保存
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
		return dto;
	}
	@ResponseBody
	@RequestMapping("/code")
	public ResultDTO code (String openid,String code){
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
		return dto;
	}
	@ResponseBody
	@RequestMapping("/shoucangs")
	public int shoucangs (Long id){
		//获取收藏院校数量
		int numbers = userService.selectShoucangNumbers(id);
		return numbers;
	}
}
