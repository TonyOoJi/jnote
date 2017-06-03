package com.jnote.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防止用户通过输入URL直接访问网页的过滤器
 */
public class DirectToPageFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String conString = "";
		conString = req.getHeader("REFERER");// 获取父url--如果不是直接输入的话就是先前的访问过来的页面，要是用户输入了，这个父url是不存在的
		if ("".equals(conString) || null == conString) { // 判断如果上一个目录为空的话，说明是用户直接输入url访问的
			String servletPath = req.getServletPath();// 当前请求url，去掉几个可以直接访问的页面
			if (servletPath.contains("index.jsp")
					|| servletPath.contains("admin/login.jsp")) { // 跳过index.jsp和登陆Login.jsp
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect("/jnote/index.jsp");// 跳回首页
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
