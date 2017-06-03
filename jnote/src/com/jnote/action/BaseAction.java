package com.jnote.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jnote.service.impl.ServiceManager;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	protected javax.servlet.http.HttpServletResponse response;
	protected javax.servlet.http.HttpServletRequest request;
	protected ServiceManager serviceManager;
//	protected String result;
	protected Map<String, String> cookies;
	protected HttpSession session;

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	/*public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}*/

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		// 得到ServiceManager
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		serviceManager = (ServiceManager) wac.getBean("ServiceManager");
		//session
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected String getCookieValue(String name) {
		javax.servlet.http.Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (!cookie.getName().equals(name))
					continue;
				return cookie.getValue();
			}
		}
		String value = request.getParameter(name);
		if (value != null)
			return value;
		return null;
	}

	protected void saveCookie(String name, String value, int maxAge) {
		javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(name,
				value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
}
