package com.yuanmaxinxi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/yyy")
public class CharsetFilter implements Filter{

	public void destroy() {
		
	}
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		request.setCharacterEncoding("utf-8");
		System.err.println("进编码过滤器");
		chain.doFilter(req, resp);
	}
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
