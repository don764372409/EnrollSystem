package com.yuanmaxinxi.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.entity.admin.Admin;
@WebFilter("/xxx")
public class LoginFilter implements Filter{
	//存放不拦截的资源的
	private List<String> list = new ArrayList<>();
	public void destroy() {
		
	}
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		
		//获取当前请求的资源
		String uri = request.getRequestURI();
		//如果是静态资源,放行
		if (uri.contains("/commons")||uri.contains("/H-ui")||uri.contains("/upload")) {
			chain.doFilter(req, resp);
			return;
		}
		//判断 当前拿到的uri是否需要拦截
		if (list.contains(uri)) {
			//如果包含  就放行
			chain.doFilter(req, resp);
			return;
		}
		//不包含  --  应该拦截
		//获取session中的登陆者
		Admin admin = (Admin)request.getSession().getAttribute("loginAdmin");
		//如果登陆者在  放行
		if (admin!=null) {
			chain.doFilter(req, resp);
			return;
		}
		//如果登陆者没有登录  就跳转到登录页面
		HttpServletResponse response = (HttpServletResponse)resp;
		response.sendRedirect("/login");
	}
	public void init(FilterConfig arg0) throws ServletException {
		list.add("/login.jsp");
		list.add("/login");
	}
}
