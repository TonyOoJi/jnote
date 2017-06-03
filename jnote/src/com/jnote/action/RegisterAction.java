package com.jnote.action;

import com.jnote.vo.User;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends BaseAction implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();// struts2模型驱动
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

	public String execute() {
		// System.out.println("user:"+user.getUsername());

		User persistenceUser = new User();
		// 将md5加密的对象持久化
		persistenceUser.setUsername(user.getUsername());
		try {
			persistenceUser.setPassword(user.getMd5Password());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user.getUsername() != null && user.getPassword() != null) {
			if (serviceManager.getUserService().saveUser(persistenceUser)) {
				// System.out.println("user:"+user.getUsername());
				return SUCCESS;
			}
		}
		// System.out.println("user:" + user.getUsername());
		return INPUT;
	}
}
