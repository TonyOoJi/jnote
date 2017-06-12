package com.jnote.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.jnote.service.IFolderService;
import com.jnote.service.IMdFileService;
import com.jnote.service.IUserInfoService;
import com.jnote.service.IUserService;

public class ServiceManager {
	private IUserService userService;
	private IUserInfoService userInfoService;
	private IFolderService folderService;
	private IMdFileService mdFileService;
	private static ApplicationContext applicationContext;
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public IFolderService getFolderService() {
		return folderService;
	}

	public void setFolderService(IFolderService folderService) {
		this.folderService = folderService;
	}

	public IMdFileService getMdFileService() {
		return mdFileService;
	}

	public void setMdFileService(IMdFileService mdFileService) {
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
