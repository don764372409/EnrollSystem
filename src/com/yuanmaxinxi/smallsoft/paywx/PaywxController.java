/**
 * 
 */
package com.yuanmaxinxi.smallsoft.paywx;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.ubalance.Ubalance;
import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UserService;
import com.yuanmaxinxi.service.ubalance.UbalanceService;
import com.yuanmaxinxi.service.ulogpay.UlogpayService;

/**
 * @author 灰飞的猪
 *
 */
@CrossOrigin
@Controller
@RequestMapping("/pay")
public class PaywxController {
	@Autowired
	private UlogpayService ulogpayService;
	@Autowired
	private UserService userService;
	@Autowired
	private UbalanceService ubalanceService;
	@RequestMapping("/weixin")
	@ResponseBody
	public ResultDTO payWeinxin(Ulogpay ulogpay) {
		ResultDTO dto=null;
		try {
			String value1 = ulogpay.getStrValue().split("元")[0];
			ulogpay.setValue(new BigDecimal(value1));
			Map<String, String> payWeixin = ulogpayService.payWeixin(ulogpay);
			Object json = JSONObject.toJSON(payWeixin);
			dto= ResultDTO.newInstance(true,json);
		} catch (Exception e) {
			e.printStackTrace();
			dto= ResultDTO.newInstance(false, e.getMessage());
		}
		return dto;
	}
	@RequestMapping("/finish")
	@ResponseBody
	public ResultDTO finish(Ulogpay ulogpay) {
		try {
			ulogpayService.finish(ulogpay);
			return ResultDTO.newInstance(true,"更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDTO.newInstance(false, e.getMessage());
		}
	}
	@RequestMapping("/pay")
	@ResponseBody
	public ResultDTO pay(Ulogpay ulogpay) {
		try {
			ulogpayService.pay(ulogpay);
			return ResultDTO.newInstance(true,"更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDTO.newInstance(false, e.getMessage());
		}
	}
	@RequestMapping("/toRecharge")
	public String toRecharge(String openid,Model model) {
		Ubalance ubalance = ubalanceService.selectOneByOpenId(openid);
		User user = userService.selectOneByOpenid(openid);
		model.addAttribute("ubalance", ubalance);
		model.addAttribute("user", user);
		model.addAttribute("openid", openid);
		model.addAttribute("account", ubalance);
		return "account/recharge";
	}
}
