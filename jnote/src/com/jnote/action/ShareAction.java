package com.jnote.action;

import com.jnote.vo.MdFile;
import com.jnote.vo.User;
import com.jnote.vo.UserInfo;

public class ShareAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mdHtml;
	private String title;
	private UserInfo userInfo;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMdHtml() {
		return mdHtml;
	}

	public void setMdHtml(String mdHtml) {
		this.mdHtml = mdHtml;
	}

	public String getSharePage(){
//		System.out.println("come in getSharePage");
		int fileid = Integer.parseInt(request.getParameter("fileid"));
		MdFile mf = this.serviceManager.getMdFileService().findFileById(fileid);
		if(mf == null){
			return INPUT;
		}else{
			if(mf.getShareurl() == null){
				mdHtml = "<h1>禁止访问--没有访问权限，这篇文章没有被分享。</h1>";
				return SUCCESS;
			}else{
				if(mf.getUserid() != null){
					Integer userid = ((User) session.getAttribute("user")).getUserid();
					if(userid != null){
						user = serviceManager.getUserService().getUserById(userid);
						userInfo = serviceManager.getUserInfoService().findUserInfoByUserId(userid);
					}
				}
				title = mf.getFilename();
				mdHtml = mf.getMdhtml();
				return SUCCESS;
			}
		}
	}
}
