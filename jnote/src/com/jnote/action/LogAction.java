package com.jnote.action;

import com.jnote.vo.User;
import com.opensymphony.xwork2.ModelDriven;

public class LogAction extends BaseAction implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private User user = new User();

	public User getModel() {
		return user;
	}

	public String login() {
		if (serviceManager.getUserService().login(user)) {
			session.setAttribute("user", user);
											System.out.println(user.getUsername() + "登录");
			return SUCCESS;
		}
		return INPUT;
	}

	public String logout() {
		session.removeAttribute("user");
		if (session.getAttribute("user") == null) {
			return SUCCESS;
		}
		return INPUT;
	}
}
