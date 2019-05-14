package com.yuanmaxinxi.smallsoft.result;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/soft/result")
@Controller
public class ResultServlet{


	@RequestMapping("/resultList")
	@ResponseBody
	public  Object xx(String number,String pId,String type) {
		System.out.println(11111);
		String name=pId;
		System.out.println(number+pId+type);
		return null;
	}
}
