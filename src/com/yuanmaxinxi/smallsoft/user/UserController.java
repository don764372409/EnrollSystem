package com.yuanmaxinxi.smallsoft.user;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UserService;
@RequestMapping("/soft/user")
@Controller
public class UserController{
	@Autowired
	private UserService userService;
	//get请求
    private static JSONObject doGet(String requestUrl) {
    	HttpClient httpClient = new DefaultHttpClient();
    	HttpResponse response = null;
        String responseContent  = null;
        com.alibaba.fastjson.JSONObject result = null;
        try {
            //创建Get请求，
            HttpGet httpGet = new HttpGet(requestUrl);
            //执行Get请求，
            response = httpClient.execute(httpGet);
            //得到响应体
            HttpEntity entity = response.getEntity();
            //获取响应内容
            responseContent  = EntityUtils.toString(entity,"UTF-8");
            //转换为map
            result = JSON.parseObject(responseContent);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return result;
    }
	@RequestMapping("/getOpenId")
	@ResponseBody
	public ResultDTO getOpenId (String code){
		try {
			JSONObject doGet = doGet("https://api.weixin.qq.com/sns/jscode2session?appId=wx934f1fc99b01220a&secret=f81900ff248a8727a5804f216534e622&js_code="+code+"&grant_type=authorization_code");
			System.out.println(doGet);
			return ResultDTO.putSuccessObj("信息查询成功.",doGet);
		} catch (Exception e) {
			return ResultDTO.putError(e.getMessage());
		}
	}
	@RequestMapping("/updateInfo")
	@ResponseBody
	public ResultDTO updateInfo (User user){
		try {
			userService.update(user);
			return ResultDTO.putSuccess("修改信息成功.");
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
	//
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
	@ResponseBody
	@RequestMapping("/selectOne")
	public User shoucangs (String openid){
		//获取收藏院校数量
		User user = userService.selectOneByOpenid(openid);
		return user;
	}
}
