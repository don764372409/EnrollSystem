/**
 * 
 */
package com.yuanmaxinxi.smallsoft.user;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuanmaxinxi.util.StringUtil;

/**
 * @author 灰飞的猪
 *
 */
@RequestMapping("/soft")
@Controller
public class xxxxx {
	@RequestMapping("/index")
	public void getCode(HttpServletRequest req,HttpServletResponse response) {
			String code = req.getParameter("code");
			if(StringUtil.isNotNullAndEmpty(code)) {
				System.out.println("1111111111");
				System.out.println(code);
				  try {
					response.sendRedirect("http://www.methodol-edu.com/SSM/soft/user/toAccount");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	        StringBuffer sb = new StringBuffer();
	        StringBuffer encodeUrl = new StringBuffer(300);
	        //公众号中配置的回调域名（网页授权回调域名）
	        String appId ="wx934f1fc99b01220a";
	        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
	        sb.append(appId);
	        String url = "";
	        try {
	            //对重定向url进行编码，官方文档要求
	            url = URLEncoder.encode("https://www.methodol-edu.com/SSM/soft/user/index", "utf-8");
	            sb.append("&redirect_uri=").append(url);
	            //网页授权的静默授权snsapi_base
	            sb.append("&response_type=code&scope=snsapi_base&state=State");
	            response.sendRedirect(sb.toString());
	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new RuntimeException("重定向url编码失败");
	        }
	}
}
