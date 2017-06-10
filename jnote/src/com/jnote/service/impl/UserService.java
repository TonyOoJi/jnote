package com.jnote.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jnote.dao.UserDAO;
import com.jnote.service.IUserService;
import com.jnote.vo.User;


public class UserService implements IUserService{
	private UserDAO userDao;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean saveUser(User user) {
		if(check(user.getUsername())){
			return false;
		}else{
			userDao.save(user);
			return true;
		}
	}

	public boolean login(User user) {
		List users = userDao.findByUsername(user.getUsername());
		if(users.size()==1){
			try {
				if(user.getMd5Password().equals(((User)(users.get(0))).getPassword())){
					user.setUserid(((User)users.get(0)).getUserid());
					user.setPassword(null);
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean check(String username){
		List users = userDao.findByUsername(username);
		if(users!=null&&users.size()==1){
			return true;
		}else 
			return false;
	}
	
/*	/**
	 * DWR调用
	 *//*
	public boolean checkUser(String username){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		ServiceManager serviceManager = (ServiceManager) wac.getBean("ServiceManager");
		List users = serviceManager.getUserService().userDao.findByUsername(username);

		if(users!=null&&users.size()==1){
			return true;
		}else 
			return false;
	}*/
	
}
