package com.yuanmaxinxi.smallsoft.user;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.ubalance.Ubalance;
import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UserService;
import com.yuanmaxinxi.service.ubalance.UbalanceService;
import com.yuanmaxinxi.service.ulogpay.UlogpayService;
@RequestMapping("/soft/user")
@Controller
public class UserController{
	@Autowired
	private UserService userService;
	@Autowired
	private UbalanceService ubalanceService;
	@Autowired
	private UlogpayService ulogpayService;
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
			dto = ResultDTO.putSuccess("");
			dto.setObj(user);
		}else {
			//用户这是第一次使用这个小程序
			dto = ResultDTO.putError("您还没有授权,点击确定跳转授权页面.");
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
			dto = ResultDTO.putSuccess("授权成功,点击进入下一步");
		} catch (Exception e) {
			dto = ResultDTO.putError(e.getMessage());
		}
		return dto;
	}
	@ResponseBody
	@RequestMapping("/code")
	public ResultDTO code (String openid,String code){
		ResultDTO dto;
		try {
			userService.bindNumber(openid,code);
			dto = ResultDTO.putSuccess("绑定成功,您的邀请人将获得50积分!点击跳转到首页");
		} catch (Exception e) {
			if (e.getMessage().contains("已经绑定")) {
				dto = ResultDTO.putError(e.getMessage());
			}else {
				dto = ResultDTO.putError(e.getMessage());
			}
		}
		return dto;
	}
	@ResponseBody
	@RequestMapping("/shoucangs")
	public ResultDTO shoucangs (Long id){
		//获取收藏院校数量
		ResultDTO dto;
		try {
			int numbers = userService.selectShoucangNumbers(id);
			dto = ResultDTO.putSuccessObj("获取成功!", numbers);
		} catch (Exception e) {
			dto =  ResultDTO.putError("获取收藏院校失败,请稍后重试.");
		}
		return dto;
	}
	@ResponseBody
	@RequestMapping("/selectOne")
	public User selectOne (String openid){
		User user = userService.selectOneByOpenid(openid);
		return user;
	}
	@ResponseBody
	@RequestMapping("/selectOneByCode")
	public ResultDTO selectOneByCode (String code){
		ResultDTO dto;
		try {
			User user = userService.selectOneByCode(code);
			dto = ResultDTO.putSuccessObj("获取成功!", user);
		} catch (Exception e) {
			e.printStackTrace();
			dto =  ResultDTO.putError(e.getMessage());
		}
		return dto;
	}
	@RequestMapping("/toAccount")
	public String toAccount(Model model,String code) {
//		JSONObject doGet = doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx4567caad1b60b9e1&secret=75f903c2c84c191b7da4ca506ff35e70&code="+code+"&grant_type=authorization_code");
//		String openid = doGet.getString("openid");
//		String openid = "oLIw75Gcx0gWb4u4P6Wl3nckpYI4";
		//先获取Code
		//获取openID
		//获取用户昵称
		User user = userService.selectOneByCode(code);
		//获取剩余积分
		String openid = user.getOpenid();
		Ubalance ubalance = ubalanceService.selectOneByOpenId(openid);
		//获取消费记录
		List<Ulogpay> list = ulogpayService.selectAllByOpenId(openid);
		model.addAttribute("ubalance", ubalance);
		model.addAttribute("code", user.getCode());
		model.addAttribute("number", user.getNumber());
		model.addAttribute("user", user);
		model.addAttribute("account", ubalance);
		model.addAttribute("list", list);
		return "account/account";
	}
	@RequestMapping("/index")
	public String getCode(HttpServletRequest req,HttpServletResponse response) {
		return "shouquan/index";
	}
	@RequestMapping("/validataWx")
	public void validataWx(String signature,String timestamp,String nonce,String echostr,HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		out.print(echostr);
		out.close();
		out = null;
	}
}
