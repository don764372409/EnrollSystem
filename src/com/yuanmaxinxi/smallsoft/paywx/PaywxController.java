/**
 * 
 */
package com.yuanmaxinxi.smallsoft.paywx;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
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
	@RequestMapping("/weixin")
	@ResponseBody
	public ResultDTO payWeinxin(Ulogpay ulogpay) {
		try {
			Map<String, String> payWeixin = ulogpayService.payWeixin(ulogpay);
			Object json = JSONObject.toJSON(payWeixin);
			return ResultDTO.newInstance(true,json);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDTO.newInstance(false, "获取失败");
		}
		
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
}
