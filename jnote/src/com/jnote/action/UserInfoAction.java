package com.jnote.action;

import com.jnote.vo.User;
import com.jnote.vo.UserInfo;
import com.opensymphony.xwork2.ModelDriven;

public class UserInfoAction extends BaseAction implements ModelDriven<UserInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserInfo userInfo = new UserInfo();
	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public UserInfo getModel() {
		return userInfo;
	}
	
	public String updateUserInfo(){
		userInfo.setUserid(((User)session.getAttribute("user")).getUserid());
		if(serviceManager.getUserInfoService().updateUserInfo(userInfo) == 1){
			return SUCCESS;
		}
		return INPUT;
	}
}
