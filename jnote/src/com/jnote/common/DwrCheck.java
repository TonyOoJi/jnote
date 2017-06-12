package com.jnote.common;

import java.util.List;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jnote.service.impl.ServiceManager;
import com.jnote.service.impl.UserService;

public class DwrCheck {
	/**
	 * DWR调用
	 */
	public boolean checkUser(String username){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		ServiceManager serviceManager = (ServiceManager) wac.getBean("ServiceManager");
		List users = ((UserService) serviceManager.getUserService()).getUserDao().findByUsername(username);
		if(users!=null&&users.size()==1){
			return true;
		}else 
			return false;
	}
}
