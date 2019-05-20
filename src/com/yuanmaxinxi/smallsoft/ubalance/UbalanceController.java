/**
 * 
 */
package com.yuanmaxinxi.smallsoft.ubalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.entity.ubalance.Ubalance;
import com.yuanmaxinxi.service.ubalance.UbalanceService;

/**
 * @author 灰飞的猪
 *
 */
@Controller
@RequestMapping("/ubalance")
public class UbalanceController {
	@Autowired
	private UbalanceService ubalanceService;
	@RequestMapping("/selectOne")
	@ResponseBody
	public Ubalance selectOneByType(String openid) {
		Ubalance obj = ubalanceService.selectOneByOpenId(openid);
		return obj;
	}
}
