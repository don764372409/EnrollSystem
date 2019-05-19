/**
 * 
 */
package com.yuanmaxinxi.smallsoft.ulogpay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.service.ulogpay.UlogpayService;

/**
 * @author 灰飞的猪
 *
 */
@Controller
@RequestMapping("/ulogpay")
public class UlogpayController {
	@Autowired
	private UlogpayService ulogpayService;
	@RequestMapping("/list")
	@ResponseBody
	public List<Ulogpay> list(String openid) {
		List<Ulogpay> obj = ulogpayService.selectAllByOpenId(openid);
		return obj;
	}
}
