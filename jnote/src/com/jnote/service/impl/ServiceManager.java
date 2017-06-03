package com.jnote.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class ServiceManager {
	private UserService userService;
	private UserInfoService userInfoService;
	private FolderService folderService;
	private MdFileService mdFileService;
	private static ApplicationContext applicationContext;
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
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		ServiceManager.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		checkApplicationContext();
		return applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}
	
	private static void checkApplicationContext(){
		if(applicationContext == null){
			throw new IllegalStateException("没有springbean对象");
		}
	}
}
