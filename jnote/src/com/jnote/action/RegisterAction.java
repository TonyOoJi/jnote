package com.jnote.action;

import com.jnote.vo.User;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends BaseAction implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();//struts2模型驱动
	private String repassword;
	
	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public User getModel() {
		return user;
	}
	
	public String execute(){
//		System.out.println("user:"+user.getUsername());
		if(serviceManager.getUserService().saveUser(user)){
//			System.out.println("user:"+user.getUsername());
			return SUCCESS;
		}
		System.out.println("user:"+user.getUsername());
		return INPUT;
	}
}
