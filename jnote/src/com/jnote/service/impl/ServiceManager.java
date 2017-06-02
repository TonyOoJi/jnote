package com.jnote.service.impl;

public class ServiceManager {
	private UserService userService;
	private UserInfoService userInfoService;
	private FolderService folderService;
	private MdFileService mdFileService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	public FolderService getFolderService() {
		return folderService;
	}
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
	public MdFileService getMdFileService() {
		return mdFileService;
	}
	public void setMdFileService(MdFileService mdFileService) {
		this.mdFileService = mdFileService;
	}
	
}
