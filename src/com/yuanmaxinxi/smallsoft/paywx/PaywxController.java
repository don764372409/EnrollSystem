/**
 * 
 */
package com.yuanmaxinxi.smallsoft.paywx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public void payWeinxin(Ulogpay ulogpay) {
		try {
			System.out.println(ulogpay);
			ulogpayService.payWeixin(ulogpay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
