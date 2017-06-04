package com.jnote.action;

import com.jnote.vo.User;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends BaseAction implements ModelDriven<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	public String execute(){
		
//		System.out.println("userservice:"+serviceManager.getUserService());
		if(serviceManager.getUserService().login(user)){
			return SUCCESS;
		}
		return INPUT;
	}

	
}
